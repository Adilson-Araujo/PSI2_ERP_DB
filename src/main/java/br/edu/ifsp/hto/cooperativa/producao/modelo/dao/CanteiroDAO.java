package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanteiroDAO {

    /**
     * Busca todos os canteiros (com base na Ordem de Produção) associados a um Talhão (Talhao_id).
     * Nota: O esquema de Canteiro usa ordem_producao_id, que está ligada ao Talhao_id via tabela 'plano'.
     */
    public List<CanteiroVO> buscarPorTalhaoId(Long talhaoId) throws SQLException {
        String sql = """
            SELECT 
                c.id, 
                c.ativo,
                c.ordem_producao_id, 
                c.nome, 
                c.area_canteiro_m2, 
                c.observacoes, 
                c.kg_gerados, 
                c.status
            FROM canteiro c
            JOIN ordem_producao op ON c.ordem_producao_id = op.id
            WHERE op.talhao_id = ? AND c.ativo = TRUE
            ORDER BY c.nome
        """;

        List<CanteiroVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, talhaoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CanteiroVO canteiro = new CanteiroVO();
                    canteiro.setId(rs.getLong("id"));
                    canteiro.setAtivo(rs.getBoolean("ativo"));
                    canteiro.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                    canteiro.setNome(rs.getString("nome"));
                    canteiro.setAreaCanteiroM2(rs.getBigDecimal("area_canteiro_m2"));
                    canteiro.setObservacoes(rs.getString("observacoes"));
                    canteiro.setKgGerados(rs.getBigDecimal("kg_gerados"));
                    canteiro.setStatus(rs.getString("status"));
                    lista.add(canteiro);
                }
            }
        }
        return lista;
    }

    /**
     * Busca todos os canteiros ativos (ativo=true) de uma Ordem de Produção.
     */
    public List<CanteiroVO> buscarPorOrdemProducaoId(Long ordemProducaoId) throws SQLException {
        String sql = """
            SELECT id, ativo, ordem_producao_id, nome, area_canteiro_m2, observacoes, kg_gerados, status
            FROM canteiro
            WHERE ordem_producao_id = ? AND ativo = TRUE
            ORDER BY nome
        """;

        List<CanteiroVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ordemProducaoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CanteiroVO canteiro = new CanteiroVO();
                    canteiro.setId(rs.getLong("id"));
                    canteiro.setAtivo(rs.getBoolean("ativo"));
                    canteiro.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                    canteiro.setNome(rs.getString("nome"));
                    canteiro.setAreaCanteiroM2(rs.getBigDecimal("area_canteiro_m2"));
                    canteiro.setObservacoes(rs.getString("observacoes"));
                    canteiro.setKgGerados(rs.getBigDecimal("kg_gerados"));
                    canteiro.setStatus(rs.getString("status"));
                    lista.add(canteiro);
                }
            }
        }
        return lista;
    }

    // Implemente inserir, atualizar, remover conforme a sua necessidade...

    /**
     * Marca todos os canteiros de uma ordem como inativos.
     */
    public void inativarCanteirosDaOrdem(Long ordemProducaoId) throws SQLException {
        String sql = "UPDATE canteiro SET ativo = FALSE WHERE ordem_producao_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, ordemProducaoId);
            ps.executeUpdate();
        }
    }

    /**
     * Conta canteiros ativos (ativo = TRUE) para um determinado talhão.
     * Utiliza a relação entre canteiro -> ordem_producao -> talhao.
     */
    public int contarCanteirosAtivosPorTalhao(Long talhaoId) throws SQLException {
        String sql = "SELECT COUNT(c.id) AS qtde FROM canteiro c JOIN ordem_producao op ON c.ordem_producao_id = op.id WHERE op.talhao_id = ? AND c.ativo = TRUE";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, talhaoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("qtde");
                }
            }
        }
        return 0;
    }

    /**
     * Busca um canteiro por ID.
     */
    public CanteiroVO buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT id, ativo, ordem_producao_id, nome, area_canteiro_m2, observacoes, kg_gerados, status
            FROM canteiro
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CanteiroVO canteiro = new CanteiroVO();
                    canteiro.setId(rs.getLong("id"));
                    canteiro.setAtivo(rs.getBoolean("ativo"));
                    canteiro.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                    canteiro.setNome(rs.getString("nome"));
                    canteiro.setAreaCanteiroM2(rs.getBigDecimal("area_canteiro_m2"));
                    canteiro.setObservacoes(rs.getString("observacoes"));
                    canteiro.setKgGerados(rs.getBigDecimal("kg_gerados"));
                    canteiro.setStatus(rs.getString("status"));
                    return canteiro;
                }
            }
        }
        return null;
    }

    /**
     * Atualiza um canteiro existente.
     */
    public void atualizar(CanteiroVO canteiro) throws SQLException {
        String sql = """
            UPDATE canteiro 
            SET ativo = ?, ordem_producao_id = ?, nome = ?, area_canteiro_m2 = ?, 
                observacoes = ?, kg_gerados = ?, status = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, canteiro.getAtivo());
            ps.setObject(2, canteiro.getOrdemProducaoId());
            ps.setString(3, canteiro.getNome());
            ps.setBigDecimal(4, canteiro.getAreaCanteiroM2());
            ps.setString(5, canteiro.getObservacoes());
            ps.setBigDecimal(6, canteiro.getKgGerados());
            ps.setString(7, canteiro.getStatus());
            ps.setLong(8, canteiro.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Insere um novo canteiro no banco de dados.
     *
     * @param canteiro objeto CanteiroVO com os dados a inserir
     * @throws SQLException se ocorrer erro na operação
     */
    public void inserir(CanteiroVO canteiro) throws SQLException {
        String sql = """
            INSERT INTO canteiro (ativo, ordem_producao_id, nome, area_canteiro_m2, observacoes, kg_gerados, status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, canteiro.getAtivo() != null ? canteiro.getAtivo() : true);
            ps.setObject(2, canteiro.getOrdemProducaoId());
            ps.setString(3, canteiro.getNome());
            ps.setBigDecimal(4, canteiro.getAreaCanteiroM2());
            ps.setString(5, canteiro.getObservacoes());
            ps.setBigDecimal(6, canteiro.getKgGerados());
            ps.setString(7, canteiro.getStatus() != null ? canteiro.getStatus() : "crescendo");

            ps.executeUpdate();

            // Captura o ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    canteiro.setId(rs.getLong(1));
                }
            }
        }
    }
}