package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.OrdemProducaoVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemProducaoDAO {

    private Connection conexao;

    public OrdemProducaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Inserir nova ordem de produção
    public void inserir(OrdemProducaoVO vo) throws SQLException {
        String sql = "INSERT INTO ordem_producao (Plano_id, quantidade, kg_total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, vo.getPlanoId());
            stmt.setInt(2, vo.getQuantidade());
            stmt.setBigDecimal(3, vo.getKgTotal());
            stmt.executeUpdate();
        }
    }

    // Atualizar uma ordem existente
    public void atualizar(OrdemProducaoVO vo) throws SQLException {
        String sql = "UPDATE ordem_producao SET Plano_id = ?, quantidade = ?, kg_total = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, vo.getPlanoId());
            stmt.setInt(2, vo.getQuantidade());
            stmt.setBigDecimal(3, vo.getKgTotal());
            stmt.setLong(4, vo.getId());
            stmt.executeUpdate();
        }
    }

    // Buscar por ID
    public OrdemProducaoVO buscarPorId(long id) throws SQLException {
        String sql = "SELECT id, Plano_id, quantidade, kg_total FROM ordem_producao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrdemProducaoVO(
                            rs.getLong("id"),
                            rs.getLong("Plano_id"),
                            rs.getInt("quantidade"),
                            rs.getBigDecimal("kg_total")
                    );
                }
            }
        }
        return null;
    }

    // Listar todas as ordens
    public List<OrdemProducaoVO> listarTodas() throws SQLException {
        List<OrdemProducaoVO> lista = new ArrayList<>();
        String sql = "SELECT id, Plano_id, quantidade, kg_total FROM ordem_producao";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrdemProducaoVO vo = new OrdemProducaoVO(
                        rs.getLong("id"),
                        rs.getLong("Plano_id"),
                        rs.getInt("quantidade"),
                        rs.getBigDecimal("kg_total")
                );
                lista.add(vo);
            }
        }
        return lista;
    }

    // Excluir uma ordem de produção
    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM ordem_producao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
