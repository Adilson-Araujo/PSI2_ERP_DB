package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.RegistrarProblemaVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrarProblemaDAO {

    private Connection conexao;

    public RegistrarProblemaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(RegistrarProblemaVO vo) throws SQLException {
        String sql = "INSERT INTO registrar_problema (ordem_producao_id, tipo_problema_id, quantidade_afetada, data_problema, observacoes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, vo.getOrdemProducaoId());
            stmt.setLong(2, vo.getTipoProblemaId());
            stmt.setInt(3, vo.getQuantidadeAfetada());
            stmt.setTimestamp(4, vo.getDataProblema());
            stmt.setString(5, vo.getObservacoes());
            stmt.executeUpdate();
        }
    }

    public void atualizar(RegistrarProblemaVO vo) throws SQLException {
        String sql = "UPDATE registrar_problema SET ordem_producao_id = ?, tipo_problema_id = ?, quantidade_afetada = ?, data_problema = ?, observacoes = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, vo.getOrdemProducaoId());
            stmt.setLong(2, vo.getTipoProblemaId());
            stmt.setInt(3, vo.getQuantidadeAfetada());
            stmt.setTimestamp(4, vo.getDataProblema());
            stmt.setString(5, vo.getObservacoes());
            stmt.setLong(6, vo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM registrar_problema WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public RegistrarProblemaVO buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM registrar_problema WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RegistrarProblemaVO(
                            rs.getLong("id"),
                            rs.getLong("ordem_producao_id"),
                            rs.getLong("tipo_problema_id"),
                            rs.getInt("quantidade_afetada"),
                            rs.getTimestamp("data_problema"),
                            rs.getString("observacoes")
                    );
                }
            }
        }
        return null;
    }

    public List<RegistrarProblemaVO> listarTodos() throws SQLException {
        List<RegistrarProblemaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM registrar_problema";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RegistrarProblemaVO vo = new RegistrarProblemaVO(
                        rs.getLong("id"),
                        rs.getLong("ordem_producao_id"),
                        rs.getLong("tipo_problema_id"),
                        rs.getInt("quantidade_afetada"),
                        rs.getTimestamp("data_problema"),
                        rs.getString("observacoes")
                );
                lista.add(vo);
            }
        }
        return lista;
    }

    public List<RegistrarProblemaVO> listarPorOrdem(long ordemId) throws SQLException {
        List<RegistrarProblemaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM registrar_problema WHERE ordem_producao_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, ordemId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RegistrarProblemaVO vo = new RegistrarProblemaVO(
                            rs.getLong("id"),
                            rs.getLong("ordem_producao_id"),
                            rs.getLong("tipo_problema_id"),
                            rs.getInt("quantidade_afetada"),
                            rs.getTimestamp("data_problema"),
                            rs.getString("observacoes")
                    );
                    lista.add(vo);
                }
            }
        }
        return lista;
    }
}
