package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.ClienteVO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public ClienteVO buscarId(long id) {
        String sql = """
            SELECT
                id,
                endereco_id,
                nome_fantasia,
                razao_social,
                telefone,
                email,
                data_cadastro,
                ativo,
                cpf_cnpj
            FROM cliente
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClienteVO vo = new ClienteVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setCpfCnpj(rs.getString("cpf_cnpj"));
                return vo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void adicionar(ClienteVO vo) {
        String sql = """
            INSERT INTO cliente (
                endereco_id,
                nome_fantasia,
                razao_social,
                telefone,
                email,
                data_cadastro,
                ativo,
                cpf_cnpj
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, vo.getEnderecoId());
            stmt.setString(2, vo.getNomeFantasia());
            stmt.setString(3, vo.getRazaoSocial());
            stmt.setString(4, vo.getTelefone());
            stmt.setString(5, vo.getEmail());
            stmt.setTimestamp(6, Timestamp.valueOf(vo.getDataCadastro()));
            stmt.setBoolean(7, vo.getAtivo());
            stmt.setString(8, vo.getCpfCnpj());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(ClienteVO vo) {
        String sql = """
            UPDATE cliente
            SET 
                endereco_id   = ?,
                nome_fantasia = ?,
                razao_social  = ?,
                telefone      = ?,
                email         = ?,
                data_cadastro = ?,
                ativo         = ?,
                cpf_cnpj      = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vo.getEnderecoId());
            stmt.setString(2, vo.getNomeFantasia());
            stmt.setString(3, vo.getRazaoSocial());
            stmt.setString(4, vo.getTelefone());
            stmt.setString(5, vo.getEmail());
            stmt.setTimestamp(6, Timestamp.valueOf(vo.getDataCadastro()));
            stmt.setBoolean(7, vo.getAtivo());
            stmt.setString(8, vo.getCpfCnpj());
            stmt.setLong(9, vo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ClienteVO> obterTodos() {
        List<ClienteVO> lista = new ArrayList<>();

        String sql = """
            SELECT
                id,
                endereco_id,
                nome_fantasia,
                razao_social,
                telefone,
                email,
                data_cadastro,
                ativo,
                cpf_cnpj
            FROM cliente
            ORDER BY id
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClienteVO vo = new ClienteVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setCpfCnpj(rs.getString("cpf_cnpj"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void remover(long id) {
        String sql = """
            UPDATE cliente
            SET ativo = FALSE
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ClienteVO buscarCnpj(String cpfCnpj) {
        String sql = """
            SELECT
                id,
                endereco_id,
                nome_fantasia,
                razao_social,
                telefone,
                email,
                data_cadastro,
                ativo,
                cpf_cnpj
            FROM cliente
            WHERE cpf_cnpj = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClienteVO vo = new ClienteVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setCpfCnpj(rs.getString("cpf_cnpj"));
                return vo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
