package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociadoDAO {

    public AssociadoVO buscarId(long id) {
        String sql = """
            SELECT
                id,
                endereco_id,
                cnpj,
                razao_social,
                nome_fantasia,
                inscricao_estadual,
                inscricao_municipal,
                telefone,
                email,
                data_cadastrado,
                ativo
            FROM associado
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AssociadoVO vo = new AssociadoVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setCnpj(rs.getString("cnpj"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                vo.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastrado(rs.getTimestamp("data_cadastrado").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                return vo;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    public void adicionar(AssociadoVO vo) {
        String sql = """
            INSERT INTO associado (
                endereco_id,
                cnpj,
                razao_social,
                nome_fantasia,
                inscricao_estadual,
                inscricao_municipal,
                telefone,
                email,
                data_cadastrado,
                ativo,
                id,
                daf,
                caf,
                pronaf,
                paa,
                pnae
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, vo.getEnderecoId());
            stmt.setString(2, vo.getCnpj());
            stmt.setString(3, vo.getRazaoSocial());
            stmt.setString(4, vo.getNomeFantasia());
            stmt.setString(5, vo.getInscricaoEstadual());
            stmt.setString(6, vo.getInscricaoMunicipal());
            stmt.setString(7, vo.getTelefone());
            stmt.setString(8, vo.getEmail());
            stmt.setTimestamp(9, Timestamp.valueOf(vo.getDataCadastrado()));
            stmt.setBoolean(10, vo.getAtivo());
            stmt.setLong(11, vo.getId());
            stmt.setString(12, vo.getDaf());
            stmt.setString(13, vo.getCaf());
            stmt.setString(14, vo.getPronaf());
            stmt.setString(15, vo.getPaa());
            stmt.setString(16, vo.getPnae());
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void atualizar(AssociadoVO vo) {
        String sql = """
            UPDATE associado
            SET
                endereco_id        = ?,
                cnpj               = ?,
                razao_social       = ?,
                nome_fantasia      = ?,
                inscricao_estadual = ?,
                inscricao_municipal = ?,
                telefone           = ?,
                email              = ?,
                data_cadastrado    = ?,
                ativo              = ?,
                daf                = ?,
                caf                = ?,
                pronaf             = ?,
                paa                = ?,
                pnae               = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vo.getEnderecoId());
            stmt.setString(2, vo.getCnpj());
            stmt.setString(3, vo.getRazaoSocial());
            stmt.setString(4, vo.getNomeFantasia());
            stmt.setString(5, vo.getInscricaoEstadual());
            stmt.setString(6, vo.getInscricaoMunicipal());
            stmt.setString(7, vo.getTelefone());
            stmt.setString(8, vo.getEmail());
            stmt.setTimestamp(9, Timestamp.valueOf(vo.getDataCadastrado()));
            stmt.setBoolean(10, vo.getAtivo());
            stmt.setString(11, vo.getDaf());
            stmt.setString(12, vo.getCaf());
            stmt.setString(13, vo.getPronaf());
            stmt.setString(14, vo.getPaa());
            stmt.setString(15, vo.getPnae());
            stmt.setLong(16, vo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<AssociadoVO> obterTodos() {
        List<AssociadoVO> lista = new ArrayList<>();

        String sql = """
            SELECT
                id,
                endereco_id,
                cnpj,
                razao_social,
                nome_fantasia,
                inscricao_estadual,
                inscricao_municipal,
                telefone,
                email,
                data_cadastrado,
                daf,
                caf,
                pronaf,
                paa,
                pnae,
                ativo
            FROM associado
            ORDER BY id
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AssociadoVO vo = new AssociadoVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setCnpj(rs.getString("cnpj"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                vo.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastrado(rs.getTimestamp("data_cadastrado").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setDaf(rs.getString("daf"));
                vo.setCaf(rs.getString("caf"));
                vo.setPronaf(rs.getString("pronaf"));
                vo.setPaa(rs.getString("paa"));
                vo.setPnae(rs.getString("pnae"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return lista;
    }

    public void remover(long id) {
        String sql = """
            UPDATE associado
            SET ativo = FALSE
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public AssociadoVO buscarCnpj(String cnpj) {
        String sql = """
            SELECT
                id,
                endereco_id,
                cnpj,
                razao_social,
                nome_fantasia,
                inscricao_estadual,
                inscricao_municipal,
                telefone,
                email,
                data_cadastrado,
                daf,
                caf,
                pronaf,
                paa,
                pnae,
                ativo
            FROM associado
            WHERE cnpj = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AssociadoVO vo = new AssociadoVO();
                vo.setId(rs.getLong("id"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setCnpj(rs.getString("cnpj"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                vo.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastrado(rs.getTimestamp("data_cadastrado").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setDaf(rs.getString("daf"));
                vo.setCaf(rs.getString("caf"));
                vo.setPronaf(rs.getString("pronaf"));
                vo.setPaa(rs.getString("paa"));
                vo.setPnae(rs.getString("pnae"));
                return vo;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }
}
