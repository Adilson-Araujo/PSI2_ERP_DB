package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalEletronicaVO;
import java.sql.*;
import java.util.*;

public class NotaFiscalEletronicaDAO {

    public NotaFiscalEletronicaDAO() {}

    public NotaFiscalEletronicaVO buscarId(long id) {
        NotaFiscalEletronicaVO vo = null;
        String sql = "SELECT * FROM nota_fiscal_eletronica WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new NotaFiscalEletronicaVO();
                    vo.setId(rs.getLong("id"));
                    vo.setAssociadoId(rs.getLong("associado_id"));
                    vo.setClienteId(rs.getInt("cliente_id"));
                    vo.setChaveAcesso(rs.getString("chave_acesso"));
                    vo.setRazaoSocial(rs.getString("razao_social"));
                    Timestamp ts_data_missao = rs.getTimestamp("data_missao");
                    if (ts_data_missao != null) {
                        vo.setDataEmissao(ts_data_missao.toLocalDateTime());
                    }
                    vo.setValorTotal(rs.getBigDecimal("valor_total"));
                    vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                    vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                    vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                    vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                    vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));
                    Timestamp ts_data_inclusao = rs.getTimestamp("data_inclusao");
                    if (ts_data_inclusao != null) {
                        vo.setDataInclusao(ts_data_inclusao.toLocalDateTime());
                    }
                    vo.setAtivo(rs.getBoolean("ativo"));
                    vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                    vo.setNumeroSerie(rs.getString("numero_serie"));
                    vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                    vo.setValorFrete(rs.getBigDecimal("valor_frete"));
                    return vo;
                }
            }
        } catch (SQLException e) {
             e.printStackTrace(); 
        }
        finally 
        { 
            try 
            { 
                conexao.close(); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace();
            } 
        }
        return null;
    }

    public String adicionar(NotaFiscalEletronicaVO vo) {
        String sql = "INSERT INTO nota_fiscal_eletronica (associado_id, cliente_id, chave_acesso, razao_social, data_missao, valor_total, tipo_ambiente, tipo_operacao, tipo_forma_emissao, tipo_status_envio_sefaz, numero_protocolo, data_inclusao, ativo, numero_nota_fiscal, numero_serie, dados_adicionais, valor_frete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            if (vo.getAssociadoId() == null) {
                p.setNull(1, Types.BIGINT); 
            } else { 
                p.setLong(1, vo.getAssociadoId());
            }
            p.setInt(2, vo.getClienteId()==null?0:vo.getClienteId());
            p.setString(3, vo.getChaveAcesso());
            p.setString(4, vo.getRazaoSocial());
            if (vo.getDataEmissao() != null) {
                p.setTimestamp(5, Timestamp.valueOf(vo.getDataEmissao()));
            } else {
                p.setTimestamp(5, null);
            }
            p.setBigDecimal(6, vo.getValorTotal());
            p.setInt(7, vo.getTipoAmbiente()==null?0:vo.getTipoAmbiente());
            p.setInt(8, vo.getTipoOperacao()==null?0:vo.getTipoOperacao());
            p.setInt(9, vo.getTipoFormaEmissao()==null?0:vo.getTipoFormaEmissao());
            p.setInt(10, vo.getTipoStatusEnvioSefaz()==null?0:vo.getTipoStatusEnvioSefaz());
            p.setInt(11, vo.getNumeroProtocolo()==null?0:vo.getNumeroProtocolo());
            if (vo.getDataInclusao() != null) {
                p.setTimestamp(12, Timestamp.valueOf(vo.getDataInclusao()));
            } else {
                p.setTimestamp(12, null);
            }
            if (vo.getAtivo() == null) {
                p.setNull(13, Types.BOOLEAN); 
            } else { 
                p.setBoolean(13, vo.getAtivo());
            }
            p.setString(14, vo.getNumeroNotaFiscal());
            p.setString(15, vo.getNumeroSerie());
            p.setString(16, vo.getDadosAdicionais());
            p.setBigDecimal(17, vo.getValorFrete());
            try (ResultSet rs = p.executeQuery()) { 
                if (rs.next()) { 
                    vo.setId(rs.getLong("id")); 
                    return "OK"; 
                } 
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return e.getMessage(); 
        }
        finally 
        { 
            try 
            { 
                conexao.close(); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace();
            } 
        }
        return "ERROR";
    }

    public String remover(long id) {
        String sql = "DELETE FROM nota_fiscal_eletronica WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "NAO_ENCONTRADO";
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return e.getMessage(); 
        }
        finally 
        { 
            try 
            { 
                conexao.close(); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace();
            } 
        }
    }

    public String atualizar(NotaFiscalEletronicaVO vo) {
        String sql = "UPDATE nota_fiscal_eletronica SET associado_id = ?, cliente_id = ?, chave_acesso = ?, razao_social = ?, data_missao = ?, valor_total = ?, tipo_ambiente = ?, tipo_operacao = ?, tipo_forma_emissao = ?, tipo_status_envio_sefaz = ?, numero_protocolo = ?, data_inclusao = ?, ativo = ?, numero_nota_fiscal = ?, numero_serie = ?, dados_adicionais = ?, valor_frete = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            if (vo.getAssociadoId() == null) {
                p.setNull(1, Types.BIGINT); 
            } else { 
                p.setLong(1, vo.getAssociadoId());
            }
            p.setInt(2, vo.getClienteId()==null?0:vo.getClienteId());
            p.setString(3, vo.getChaveAcesso());
            p.setString(4, vo.getRazaoSocial());
            if (vo.getDataEmissao() != null) {
                p.setTimestamp(5, Timestamp.valueOf(vo.getDataEmissao()));
            } else {
                p.setTimestamp(5, null);
            }
            p.setBigDecimal(6, vo.getValorTotal());
            p.setInt(7, vo.getTipoAmbiente()==null?0:vo.getTipoAmbiente());
            p.setInt(8, vo.getTipoOperacao()==null?0:vo.getTipoOperacao());
            p.setInt(9, vo.getTipoFormaEmissao()==null?0:vo.getTipoFormaEmissao());
            p.setInt(10, vo.getTipoStatusEnvioSefaz()==null?0:vo.getTipoStatusEnvioSefaz());
            p.setInt(11, vo.getNumeroProtocolo()==null?0:vo.getNumeroProtocolo());
            if (vo.getDataInclusao() != null) {
                p.setTimestamp(12, Timestamp.valueOf(vo.getDataInclusao()));
            } else {
                p.setTimestamp(12, null);
            }
            if (vo.getAtivo() == null) {
                p.setNull(13, Types.BOOLEAN); 
            } else { 
                p.setBoolean(13, vo.getAtivo());
            }
            p.setString(14, vo.getNumeroNotaFiscal());
            p.setString(15, vo.getNumeroSerie());
            p.setString(16, vo.getDadosAdicionais());
            p.setBigDecimal(17, vo.getValorFrete());
            p.setLong(18, vo.getId());
            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "NAO_ATUALIZADO";
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return e.getMessage(); 
        }
        finally 
        { 
            try 
            { 
                conexao.close(); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace();
            } 
        }
    }

    public ArrayList<NotaFiscalEletronicaVO> obterTodos() {
        ArrayList<NotaFiscalEletronicaVO> list = new ArrayList<>();
        String sql = "SELECT * FROM nota_fiscal_eletronica";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                NotaFiscalEletronicaVO vo = new NotaFiscalEletronicaVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setClienteId(rs.getInt("cliente_id"));
                vo.setChaveAcesso(rs.getString("chave_acesso"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                Timestamp ts_data_missao = rs.getTimestamp("data_missao");
                if (ts_data_missao != null) {
                    vo.setDataEmissao(ts_data_missao.toLocalDateTime());
                }
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setTipoAmbiente(rs.getInt("tipo_ambiente"));
                vo.setTipoOperacao(rs.getInt("tipo_operacao"));
                vo.setTipoFormaEmissao(rs.getInt("tipo_forma_emissao"));
                vo.setTipoStatusEnvioSefaz(rs.getInt("tipo_status_envio_sefaz"));
                vo.setNumeroProtocolo(rs.getInt("numero_protocolo"));
                Timestamp ts_data_inclusao = rs.getTimestamp("data_inclusao");
                if (ts_data_inclusao != null) {
                    vo.setDataInclusao(ts_data_inclusao.toLocalDateTime());
                }
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
                vo.setNumeroSerie(rs.getString("numero_serie"));
                vo.setDadosAdicionais(rs.getString("dados_adicionais"));
                vo.setValorFrete(rs.getBigDecimal("valor_frete"));
                list.add(vo);
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        finally 
        { 
            try 
            { 
                conexao.close(); 
            } 
            catch (SQLException e) 
            { 
                e.printStackTrace();
            } 
        }
        return list;
    }
}
