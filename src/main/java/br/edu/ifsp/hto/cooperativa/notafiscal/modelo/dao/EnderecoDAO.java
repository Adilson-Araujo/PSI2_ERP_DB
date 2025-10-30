package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.EnderecoVO;
import java.sql.*;
import java.util.*;

public class EnderecoDAO {

    public EnderecoDAO() {}

    public EnderecoVO buscarId(long id) {
        EnderecoVO vo = null;
        String sql = "SELECT * FROM endereco WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new EnderecoVO();
                    vo.setId(rs.getLong("id"));
                    vo.setEstado(rs.getString("estado"));
                    vo.setCidade(rs.getString("cidade"));
                    vo.setBairro(rs.getString("bairro"));
                    vo.setRua(rs.getString("rua"));
                    vo.setNumero(rs.getInt("numero"));
                    vo.setCep(rs.getString("cep"));
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

    public String adicionar(EnderecoVO vo) {
        String sql = "INSERT INTO endereco (estado, cidade, bairro, rua, numero, cep) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getEstado());
            p.setString(2, vo.getCidade());
            p.setString(3, vo.getBairro());
            p.setString(4, vo.getRua());
            p.setInt(5, vo.getNumero()==null?0:vo.getNumero());
            p.setString(6, vo.getCep());
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
        String sql = "DELETE FROM endereco WHERE id = ?";
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

    public String atualizar(EnderecoVO vo) {
        String sql = "UPDATE endereco SET estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, cep = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getEstado());
            p.setString(2, vo.getCidade());
            p.setString(3, vo.getBairro());
            p.setString(4, vo.getRua());
            p.setInt(5, vo.getNumero()==null?0:vo.getNumero());
            p.setString(6, vo.getCep());
            p.setLong(7, vo.getId());
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

    public ArrayList<EnderecoVO> obterTodos() {
        ArrayList<EnderecoVO> list = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EnderecoVO vo = new EnderecoVO();
                vo.setId(rs.getLong("id"));
                vo.setEstado(rs.getString("estado"));
                vo.setCidade(rs.getString("cidade"));
                vo.setBairro(rs.getString("bairro"));
                vo.setRua(rs.getString("rua"));
                vo.setNumero(rs.getInt("numero"));
                vo.setCep(rs.getString("cep"));
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
