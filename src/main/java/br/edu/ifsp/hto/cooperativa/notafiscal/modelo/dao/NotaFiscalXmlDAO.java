package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import java.sql.*;
import java.util.*;

public class NotaFiscalXmlDAO {

    public NotaFiscalXmlDAO() {}

    public NotaFiscalXmlVO buscarId(long id) {
        NotaFiscalXmlVO vo = null;
        String sql = "SELECT * FROM nota_fiscal_xml WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new NotaFiscalXmlVO();
                    vo.setId(rs.getLong("id"));
                    vo.setHash(rs.getString("hash"));
                    vo.setConteudo(rs.getString("conteudo"));
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

    public String adicionar(NotaFiscalXmlVO vo) {
        String sql = "INSERT INTO nota_fiscal_xml (hash, conteudo) VALUES (?, ?) RETURNING id";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getHash());
            p.setString(2, vo.getConteudo());
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
        String sql = "DELETE FROM nota_fiscal_xml WHERE id = ?";
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

    public String atualizar(NotaFiscalXmlVO vo) {
        String sql = "UPDATE nota_fiscal_xml SET hash = ?, conteudo = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getHash());
            p.setString(2, vo.getConteudo());
            p.setLong(3, vo.getId());
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

    public ArrayList<NotaFiscalXmlVO> obterTodos() {
        ArrayList<NotaFiscalXmlVO> list = new ArrayList<>();
        String sql = "SELECT * FROM nota_fiscal_xml";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                NotaFiscalXmlVO vo = new NotaFiscalXmlVO();
                vo.setId(rs.getLong("id"));
                vo.setHash(rs.getString("hash"));
                vo.setConteudo(rs.getString("conteudo"));
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
