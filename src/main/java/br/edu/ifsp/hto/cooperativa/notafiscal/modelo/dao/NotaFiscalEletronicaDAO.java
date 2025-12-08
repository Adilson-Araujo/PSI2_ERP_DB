package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalEletronicaVO;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalEletronicaDAO {

    // ------------------------------------------------------------
    // Buscar por ID
    // ------------------------------------------------------------
    public NotaFiscalEletronicaVO buscarId(Long id) {
        String sql = """
            SELECT 
                id,
                associado_id,
                cliente_id,
                chave_acesso,
                razao_social,
                data_emissao,
                valor_total,
                tipo_ambiente,
                tipo_operacao,
                tipo_forma_emissao,
                tipo_status_envio_sefaz,
                numero_protocolo,
                data_inclusao,
                ativo,
                numero_nota_fiscal,
                numero_serie,
                dados_adicionais,
                valor_frete
            FROM nota_fiscal_eletronica
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                NotaFiscalEletronicaVO vo = new NotaFiscalEletronicaVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setClienteId(rs.getLong("cliente_id"));
                vo.setChaveAcesso(rs.getString("chave_acesso"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                Timestamp t1 = rs.getTimestamp("data_emissao");
                if (t1 != null) vo.setDataEmissao(t1.toLocalDateTime());
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));

                Timestamp t2 = rs.getTimestamp("data_inclusao");
                if (t2 != null) vo.setDataInclusao(t2.toLocalDateTime());

                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                vo.setNumeroSerie(rs.getString("numero_serie"));
                vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                vo.setValorFrete(rs.getBigDecimal("valor_frete"));

                return vo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ------------------------------------------------------------
    // Adicionar
    // ------------------------------------------------------------
    public void adicionar(NotaFiscalEletronicaVO vo) {
        String sql = """
        INSERT INTO nota_fiscal_eletronica (
            associado_id,
            cliente_id,
            chave_acesso,
            razao_social,
            data_emissao,
            valor_total,
            tipo_ambiente,
            tipo_operacao,
            tipo_forma_emissao,
            tipo_status_envio_sefaz,
            numero_protocolo,
            data_inclusao,
            ativo,
            numero_nota_fiscal,
            numero_serie,
            dados_adicionais,
            valor_frete,
            id
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            p.setLong(1, vo.getAssociadoId());
            p.setLong(2, vo.getClienteId());
            p.setString(3, vo.getChaveAcesso());
            p.setString(4, vo.getRazaoSocial());

            if (vo.getDataEmissao() != null)
                p.setTimestamp(5, Timestamp.valueOf(vo.getDataEmissao()));
            else
                p.setNull(5, Types.TIMESTAMP);

            p.setBigDecimal(6, vo.getValorTotal());
            p.setInt(7, vo.getTipoAmbiente());
            p.setInt(8, vo.getTipoOperacao());
            p.setInt(9, vo.getTipoFormaEmissao());
            p.setInt(10, vo.getTipoStatusEnvioSefaz());
            p.setInt(11, vo.getNumeroProtocolo());

            if (vo.getDataInclusao() != null)
                p.setTimestamp(12, Timestamp.valueOf(vo.getDataInclusao()));
            else
                p.setNull(12, Types.TIMESTAMP);

            p.setBoolean(13, vo.getAtivo() != null ? vo.getAtivo() : true);
            p.setString(14, vo.getNumeroNotaFiscal());
            p.setString(15, vo.getNumeroSerie());
            p.setString(16, vo.getDadosAdicionais());
            p.setBigDecimal(17, vo.getValorFrete());
            p.setLong(18, vo.getId());
            p.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Nota Fiscal", e);
        }
    }

    // ------------------------------------------------------------
    // Atualizar
    // ------------------------------------------------------------
    public void atualizar(NotaFiscalEletronicaVO vo) {
        String sql = """
            UPDATE nota_fiscal_eletronica
            SET
                associado_id            = ?,
                cliente_id              = ?,
                chave_acesso            = ?,
                razao_social            = ?,
                data_emissao            = ?,
                valor_total             = ?,
                tipo_ambiente           = ?,
                tipo_operacao           = ?,
                tipo_forma_emissao      = ?,
                tipo_status_envio_sefaz = ?,
                numero_protocolo        = ?,
                data_inclusao           = ?,
                ativo                   = ?,
                numero_nota_fiscal      = ?,
                numero_serie            = ?,
                dados_adicionais        = ?,
                valor_frete             = ?
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, vo.getAssociadoId());
            p.setLong(2, vo.getClienteId());
            p.setString(3, vo.getChaveAcesso());
            p.setString(4, vo.getRazaoSocial());

            if (vo.getDataEmissao() != null)
                p.setTimestamp(5, Timestamp.valueOf(vo.getDataEmissao()));
            else
                p.setTimestamp(5, null);

            p.setBigDecimal(6, vo.getValorTotal());
            p.setInt(7, vo.getTipoAmbiente());
            p.setInt(8, vo.getTipoOperacao());
            p.setInt(9, vo.getTipoFormaEmissao());
            p.setInt(10, vo.getTipoStatusEnvioSefaz());
            p.setInt(11, vo.getNumeroProtocolo());

            if (vo.getDataInclusao() != null)
                p.setTimestamp(12, Timestamp.valueOf(vo.getDataInclusao()));
            else
                p.setTimestamp(12, null);

            p.setBoolean(13, vo.getAtivo());
            p.setString(14, vo.getNumeroNotaFiscal());
            p.setString(15, vo.getNumeroSerie());
            p.setString(16, vo.getDadosAdicionais());
            p.setBigDecimal(17, vo.getValorFrete());

            p.setLong(18, vo.getId());

            p.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Obter Todos
    // ------------------------------------------------------------
    public ArrayList<NotaFiscalEletronicaVO> obterTodos() {

        String sql = """
            SELECT 
                id,
                associado_id,
                cliente_id,
                chave_acesso,
                razao_social,
                data_emissao,
                valor_total,
                tipo_ambiente,
                tipo_operacao,
                tipo_forma_emissao,
                tipo_status_envio_sefaz,
                numero_protocolo,
                data_inclusao,
                ativo,
                numero_nota_fiscal,
                numero_serie,
                dados_adicionais,
                valor_frete
            FROM nota_fiscal_eletronica
            """;

        ArrayList<NotaFiscalEletronicaVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            while (rs.next()) {
                NotaFiscalEletronicaVO vo = new NotaFiscalEletronicaVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setClienteId(rs.getLong("cliente_id"));
                vo.setChaveAcesso(rs.getString("chave_acesso"));
                vo.setRazaoSocial(rs.getString("razao_social"));

                Timestamp t1 = rs.getTimestamp("data_emissao");
                if (t1 != null) vo.setDataEmissao(t1.toLocalDateTime());

                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));

                Timestamp t2 = rs.getTimestamp("data_inclusao");
                if (t2 != null) vo.setDataInclusao(t2.toLocalDateTime());

                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                vo.setNumeroSerie(rs.getString("numero_serie"));
                vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                vo.setValorFrete(rs.getBigDecimal("valor_frete"));

                lista.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return lista;
    }

    // ------------------------------------------------------------
    // Remover (soft delete) → ativo = false
    // ------------------------------------------------------------
    public void remover(Long id) {
        String sql = """
            UPDATE nota_fiscal_eletronica
            SET ativo = FALSE
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, id);
            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NotaFiscalEletronicaVO buscarChaveAcesso(String chaveAcesso){
        String sql = """
            SELECT 
                id,
                associado_id,
                cliente_id,
                chave_acesso,
                razao_social,
                data_emissao,
                valor_total,
                tipo_ambiente,
                tipo_operacao,
                tipo_forma_emissao,
                tipo_status_envio_sefaz,
                numero_protocolo,
                data_inclusao,
                ativo,
                numero_nota_fiscal,
                numero_serie,
                dados_adicionais,
                valor_frete
            FROM nota_fiscal_eletronica
            WHERE chave_acesso = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, chaveAcesso);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                NotaFiscalEletronicaVO vo = new NotaFiscalEletronicaVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setClienteId(rs.getLong("cliente_id"));
                vo.setChaveAcesso(rs.getString("chave_acesso"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                Timestamp t1 = rs.getTimestamp("data_emissao");
                if (t1 != null) vo.setDataEmissao(t1.toLocalDateTime());
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));

                Timestamp t2 = rs.getTimestamp("data_inclusao");
                if (t2 != null) vo.setDataInclusao(t2.toLocalDateTime());

                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                vo.setNumeroSerie(rs.getString("numero_serie"));
                vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                vo.setValorFrete(rs.getBigDecimal("valor_frete"));

                return vo;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    public List<NotaFiscalEletronicaVO> buscarAssociado(Long id) {
        var lista = new ArrayList<NotaFiscalEletronicaVO>();
        String sql = """
            SELECT 
                id,
                associado_id,
                cliente_id,
                chave_acesso,
                razao_social,
                data_emissao,
                valor_total,
                tipo_ambiente,
                tipo_operacao,
                tipo_forma_emissao,
                tipo_status_envio_sefaz,
                numero_protocolo,
                data_inclusao,
                ativo,
                numero_nota_fiscal,
                numero_serie,
                dados_adicionais,
                valor_frete
            FROM nota_fiscal_eletronica
            WHERE associado_id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                NotaFiscalEletronicaVO vo = new NotaFiscalEletronicaVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setClienteId(rs.getLong("cliente_id"));
                vo.setChaveAcesso(rs.getString("chave_acesso"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                Timestamp t1 = rs.getTimestamp("data_emissao");
                if (t1 != null) vo.setDataEmissao(t1.toLocalDateTime());
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));

                Timestamp t2 = rs.getTimestamp("data_inclusao");
                if (t2 != null) vo.setDataInclusao(t2.toLocalDateTime());

                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                vo.setNumeroSerie(rs.getString("numero_serie"));
                vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                vo.setValorFrete(rs.getBigDecimal("valor_frete"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return lista;
    }
}
