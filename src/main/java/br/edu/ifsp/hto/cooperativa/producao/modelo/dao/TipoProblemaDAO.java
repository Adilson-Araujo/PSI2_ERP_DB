package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TipoProblemaVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoProblemaDAO {

    private Connection conexao;

    public TipoProblemaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(TipoProblemaVO vo) throws SQLException {
        String sql = "INSERT INTO tipo_problema (descricao) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, vo.getDescricao());
            stmt.executeUpdate();
        }
    }

    public void atualizar(TipoProblemaVO vo) throws SQLException {
        String sql = "UPDATE tipo_problema SET descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, vo.getDescricao());
            stmt.setLong(2, vo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM tipo_problema WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public TipoProblemaVO buscarPorId(long id) throws SQLException {
        String sql = "SELECT id, descricao FROM tipo_problema WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TipoProblemaVO(rs.getLong("id"), rs.getString("descricao"));
                }
            }
        }
        return null;
    }

    public List<TipoProblemaVO> listarTodos() throws SQLException {
        List<TipoProblemaVO> lista = new ArrayList<>();
        String sql = "SELECT id, descricao FROM tipo_problema";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new TipoProblemaVO(rs.getLong("id"), rs.getString("descricao")));
            }
        }
        return lista;
    }
}
