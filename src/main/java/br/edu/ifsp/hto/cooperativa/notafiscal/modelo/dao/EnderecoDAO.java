package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    public EnderecoVO buscarId(long id) {
        String sql = """
            SELECT 
                id,
                estado,
                cidade,
                bairro,
                rua,
                numero,
                cep
            FROM endereco
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                EnderecoVO vo = new EnderecoVO();
                vo.setId(rs.getLong("id"));
                vo.setEstado(rs.getString("estado"));
                vo.setCidade(rs.getString("cidade"));
                vo.setBairro(rs.getString("bairro"));
                vo.setRua(rs.getString("rua"));
                vo.setNumero(rs.getInt("numero"));
                vo.setCep(rs.getString("cep"));
                return vo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adicionar(EnderecoVO vo) {
        String sql = """
            INSERT INTO endereco (
                estado,
                cidade,
                bairro,
                rua,
                numero,
                cep
            ) VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, vo.getEstado());
            stmt.setString(2, vo.getCidade());
            stmt.setString(3, vo.getBairro());
            stmt.setString(4, vo.getRua());
            stmt.setInt(5, vo.getNumero());
            stmt.setString(6, vo.getCep());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(EnderecoVO vo) {
        String sql = """
            UPDATE endereco
            SET estado = ?,
                cidade = ?,
                bairro = ?,
                rua = ?,
                numero = ?,
                cep = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vo.getEstado());
            stmt.setString(2, vo.getCidade());
            stmt.setString(3, vo.getBairro());
            stmt.setString(4, vo.getRua());
            stmt.setInt(5, vo.getNumero());
            stmt.setString(6, vo.getCep());
            stmt.setLong(7, vo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnderecoVO> obterTodos() {
        List<EnderecoVO> lista = new ArrayList<>();

        String sql = """
            SELECT 
                id,
                estado,
                cidade,
                bairro,
                rua,
                numero,
                cep
            FROM endereco
            ORDER BY id
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EnderecoVO vo = new EnderecoVO();
                vo.setId(rs.getLong("id"));
                vo.setEstado(rs.getString("estado"));
                vo.setCidade(rs.getString("cidade"));
                vo.setBairro(rs.getString("bairro"));
                vo.setRua(rs.getString("rua"));
                vo.setNumero(rs.getInt("numero"));
                vo.setCep(rs.getString("cep"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
