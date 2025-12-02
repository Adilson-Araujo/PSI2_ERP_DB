package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemProducaoDAO {

    private Connection conn;

    // construtor correto
    public OrdemProducaoDAO(Connection conn) {
        this.conn = conn;
    }
    
    // CREATE
    public void inserir(OrdemProducaoVO vo) throws SQLException {
        String sql = "INSERT INTO ordem_producao (" +
                "plano_id, especie_id, talhao_id, nome_plano, descricao, data_inicio, data_fim, " +
                "observacoes, area_cultivo, data_execucao, quantidade_kg, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        System.out.println("[DEBUG] OrdemProducaoDAO.inserir - SQL: " + sql);
        System.out.println("[DEBUG] Valores: planoId=" + vo.getPlanoId() + ", especieId=" + vo.getEspecieId() + 
                           ", talhaoId=" + vo.getTalhaoId() + ", status=" + vo.getStatus());

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setObject(1, vo.getPlanoId());
        ps.setObject(2, vo.getEspecieId());
        ps.setObject(3, vo.getTalhaoId());
        ps.setString(4, vo.getNomePlano());
        ps.setString(5, vo.getDescricao());
        ps.setTimestamp(6, vo.getDataInicio() == null ? null : new Timestamp(vo.getDataInicio().getTime()));
        ps.setTimestamp(7, vo.getDataFim() == null ? null : new Timestamp(vo.getDataFim().getTime()));
        ps.setString(8, vo.getObservacoes());
        ps.setObject(9, vo.getAreaCultivo());
        ps.setDate(10, vo.getDataExecucao() == null ? null : new Date(vo.getDataExecucao().getTime()));
        ps.setObject(11, vo.getQuantidadeKg());
        ps.setString(12, vo.getStatus());

        System.out.println("[DEBUG] Executando INSERT...");
        ps.executeUpdate();
        System.out.println("[DEBUG] INSERT executado com sucesso");

        // Captura o ID gerado
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                Long geradoId = rs.getLong(1);
                vo.setId(geradoId);
                System.out.println("[DEBUG] ID gerado: " + geradoId);
            } else {
                System.out.println("[DEBUG] AVISO: Nenhuma chave gerada!");
            }
        }
        
        ps.close();
    }

    // READ (by id)
    public OrdemProducaoVO buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM ordem_producao WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();

        OrdemProducaoVO vo = null;

        if (rs.next()) {
            vo = converter(rs);
        }

        rs.close();
        ps.close();

        return vo;
    }

    // READ ALL
    public List<OrdemProducaoVO> listarTodos() throws SQLException {
        String sql = "SELECT * FROM ordem_producao ORDER BY id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<OrdemProducaoVO> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(converter(rs));
        }

        rs.close();
        ps.close();

        return lista;
    }

    // READ by Area (retorna ordens ativas de uma área específica)
    public List<OrdemProducaoVO> listarPorAreaId(Long areaId) throws SQLException {
        String sql = """
            SELECT DISTINCT op.*, t.nome as talhao_nome FROM ordem_producao op
            JOIN talhao t ON op.talhao_id = t.id
            WHERE t.area_id = ? AND op.status != 'deletado'
            ORDER BY op.id
        """;
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, areaId);
        ResultSet rs = ps.executeQuery();

        List<OrdemProducaoVO> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(converter(rs));
        }

        rs.close();
        ps.close();

        return lista;
    }

    // UPDATE
    public void atualizar(OrdemProducaoVO vo) throws SQLException {
        String sql = "UPDATE ordem_producao SET " +
                "plano_id=?, especie_id=?, talhao_id=?, nome_plano=?, descricao=?, data_inicio=?, data_fim=?, " +
                "observacoes=?, area_cultivo=?, data_execucao=?, quantidade_kg=?, status=? " +
                "WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, vo.getPlanoId());
        ps.setObject(2, vo.getEspecieId());
        ps.setObject(3, vo.getTalhaoId());
        ps.setString(4, vo.getNomePlano());
        ps.setString(5, vo.getDescricao());
        ps.setTimestamp(6, vo.getDataInicio() == null ? null : new Timestamp(vo.getDataInicio().getTime()));
        ps.setTimestamp(7, vo.getDataFim() == null ? null : new Timestamp(vo.getDataFim().getTime()));
        ps.setString(8, vo.getObservacoes());
        ps.setObject(9, vo.getAreaCultivo());
        ps.setDate(10, vo.getDataExecucao() == null ? null : new Date(vo.getDataExecucao().getTime()));
        ps.setObject(11, vo.getQuantidadeKg());
        ps.setString(12, vo.getStatus());
        ps.setLong(13, vo.getId());

        ps.executeUpdate();
        ps.close();
    }

    // DELETE (marca como deletado)
    public void deletar(Long ordemId) throws SQLException {
        String sql = "UPDATE ordem_producao SET status = 'deletado' WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, ordemId);
        ps.executeUpdate();
        ps.close();
    }

    // Conversão ResultSet → VO
    private OrdemProducaoVO converter(ResultSet rs) throws SQLException {
        OrdemProducaoVO vo = new OrdemProducaoVO();

        vo.setId(rs.getLong("id"));
        vo.setPlanoId((Integer) rs.getObject("plano_id"));
        vo.setEspecieId((Long) rs.getObject("especie_id"));
        vo.setTalhaoId((Long) rs.getObject("talhao_id"));
        
        // Tenta pegar o nome do talhão (pode não existir em todos os queries)
        try {
            vo.setNomeTalhao(rs.getString("talhao_nome"));
        } catch (SQLException e) {
            vo.setNomeTalhao(null);
        }
        
        vo.setNomePlano(rs.getString("nome_plano"));
        vo.setDescricao(rs.getString("descricao"));
        vo.setDataInicio(rs.getTimestamp("data_inicio"));
        vo.setDataFim(rs.getTimestamp("data_fim"));
        vo.setObservacoes(rs.getString("observacoes"));
        vo.setAreaCultivo(rs.getDouble("area_cultivo"));
        vo.setDataExecucao(rs.getDate("data_execucao"));
        vo.setQuantidadeKg(rs.getDouble("quantidade_kg"));
        vo.setStatus(rs.getString("status"));

        return vo;
    }
}
