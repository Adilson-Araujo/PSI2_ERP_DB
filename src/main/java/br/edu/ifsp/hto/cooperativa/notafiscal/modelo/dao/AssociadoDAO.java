package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.AssociadoVO;
import java.sql.*;
import java.util.*;

public class AssociadoDAO {

    public AssociadoDAO() {}

    public AssociadoVO buscarId(long id) {
        AssociadoVO vo = null;
        String sql = "SELECT * FROM associado WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new AssociadoVO();
                    vo.setId(rs.getLong("id"));
                    vo.setCnpj(rs.getString("cnpj"));
                    vo.setRazaoSocial(rs.getString("razao_social"));
                    vo.setNomeFantasia(rs.getString("nome_fantasia"));
                    vo.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                    vo.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                    vo.setEnderecoId(rs.getLong("endereco_id"));
                    vo.setTelefone(rs.getString("telefone"));
                    vo.setEmail(rs.getString("email"));
                    vo.setDataCadastrado(rs.getTimestamp("data_cadastrado").toLocalDateTime());
                    vo.setAtivo(rs.getBoolean("ativo"));
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

    public String adicionar(AssociadoVO vo) {
        String sql = "INSERT INTO associado (cnpj, razao_social, nome_fantasia, inscricao_estadual, inscricao_municipal, endereco_id, telefone, email, data_cadastrado, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getCnpj());
            p.setString(2, vo.getRazaoSocial());
            p.setString(3, vo.getNomeFantasia());
            p.setString(4, vo.getInscricaoEstadual());
            p.setString(5, vo.getInscricaoMunicipal());
            if (vo.getEnderecoId() == null) {
                p.setNull(6, Types.BIGINT); 
            } else {
                p.setLong(6, vo.getEnderecoId());
            }

            p.setString(7, vo.getTelefone());
            p.setString(8, vo.getEmail());
            p.setTimestamp(9, Timestamp.valueOf(vo.getDataCadastrado()));
            p.setBoolean(10, vo.getAtivo());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                vo.setId(rs.getLong("id")); 
                return "OK"; 
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
        return "ALGO DEU ERRADO";
    }

    public String remover(long id) {
        String sql = "DELETE FROM associado WHERE id = ?";
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

    public String atualizar(AssociadoVO vo) {
        String sql = "UPDATE associado SET cnpj = ?, razao_social = ?, nome_fantasia = ?, inscricao_estadual = ?, inscricao_municipal = ?, endereco_id = ?, telefone = ?, email = ?, data_cadastrado = ?, ativo = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getCnpj());
            p.setString(2, vo.getRazaoSocial());
            p.setString(3, vo.getNomeFantasia());
            p.setString(4, vo.getInscricaoEstadual());
            p.setString(5, vo.getInscricaoMunicipal());
            if (vo.getEnderecoId() == null) {
                p.setNull(6, Types.BIGINT); 
            } else {
                p.setLong(6, vo.getEnderecoId());
            }

            p.setString(7, vo.getTelefone());
            p.setString(8, vo.getEmail());
            p.setTimestamp(9, Timestamp.valueOf(vo.getDataCadastrado()));
            p.setBoolean(10, vo.getAtivo());
            p.setLong(11, vo.getId());
            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "NÃO ATUALIZADO";
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

    public ArrayList<AssociadoVO> obterTodos() {
        ArrayList<AssociadoVO> list = new ArrayList<>();
        String sql = "SELECT id FROM associado";
        Connection conexao = null;
        try { 
            conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql); 
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                AssociadoVO vo = new AssociadoVO();
                vo.setId(rs.getLong("id"));
                vo.setCnpj(rs.getString("cnpj"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                vo.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastrado(rs.getTimestamp("data_cadastrado").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
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
