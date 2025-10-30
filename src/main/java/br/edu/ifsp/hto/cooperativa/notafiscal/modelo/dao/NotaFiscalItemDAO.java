package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;
import java.sql.*;
import java.util.*;

public class NotaFiscalItemDAO {

    public NotaFiscalItemDAO() {}

    public NotaFiscalItemVO buscarId(long id) {
        NotaFiscalItemVO vo = null;
        String sql = "SELECT * FROM nota_fiscal_item WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new NotaFiscalItemVO();
                    vo.setId(rs.getLong("id"));
                    vo.setProdutoId(rs.getInt("produto_id"));
                    vo.setNotaFiscalEletronicaId(rs.getLong("nota_fiscal_eletronica_id"));
                    vo.setCfop(rs.getString("cfop"));
                    vo.setNcm(rs.getString("ncm"));
                    vo.setQuantidade(rs.getInt("quantidade"));
                    vo.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                    vo.setValorTotal(rs.getBigDecimal("valor_total"));
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

    public String adicionar(NotaFiscalItemVO vo) {
        String sql = "INSERT INTO nota_fiscal_item (produto_id, nota_fiscal_eletronica_id, cfop, ncm, quantidade, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setInt(1, vo.getProdutoId()==null?0:vo.getProdutoId());
            if (vo.getNotaFiscalEletronicaId() == null) {
                p.setNull(2, Types.BIGINT); 
            } else { 
                p.setLong(2, vo.getNotaFiscalEletronicaId());
            }
            p.setString(3, vo.getCfop());
            p.setString(4, vo.getNcm());
            p.setInt(5, vo.getQuantidade()==null?0:vo.getQuantidade());
            p.setBigDecimal(6, vo.getValorUnitario());
            p.setBigDecimal(7, vo.getValorTotal());
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
        String sql = "DELETE FROM nota_fiscal_item WHERE id = ?";
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

    public String atualizar(NotaFiscalItemVO vo) {
        String sql = "UPDATE nota_fiscal_item SET produto_id = ?, nota_fiscal_eletronica_id = ?, cfop = ?, ncm = ?, quantidade = ?, valor_unitario = ?, valor_total = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setInt(1, vo.getProdutoId()==null?0:vo.getProdutoId());
            if (vo.getNotaFiscalEletronicaId() == null) {
                p.setNull(2, Types.BIGINT); 
            } else { 
                p.setLong(2, vo.getNotaFiscalEletronicaId());
            }
            p.setString(3, vo.getCfop());
            p.setString(4, vo.getNcm());
            p.setInt(5, vo.getQuantidade()==null?0:vo.getQuantidade());
            p.setBigDecimal(6, vo.getValorUnitario());
            p.setBigDecimal(7, vo.getValorTotal());
            p.setLong(8, vo.getId());
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

    public ArrayList<NotaFiscalItemVO> obterTodos() {
        ArrayList<NotaFiscalItemVO> list = new ArrayList<>();
        String sql = "SELECT * FROM nota_fiscal_item";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                NotaFiscalItemVO vo = new NotaFiscalItemVO();
                vo.setId(rs.getLong("id"));
                vo.setProdutoId(rs.getInt("produto_id"));
                vo.setNotaFiscalEletronicaId(rs.getLong("nota_fiscal_eletronica_id"));
                vo.setCfop(rs.getString("cfop"));
                vo.setNcm(rs.getString("ncm"));
                vo.setQuantidade(rs.getInt("quantidade"));
                vo.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
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
