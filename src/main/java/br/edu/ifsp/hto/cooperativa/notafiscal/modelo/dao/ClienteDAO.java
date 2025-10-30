package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.ClienteVO;
import java.sql.*;
import java.util.*;

public class ClienteDAO {

    public ClienteDAO() {}

    public ClienteVO buscarId(long id) {
        ClienteVO vo = null;
        String sql = "SELECT id, nome_fantasia, razao_social, endereco_id, telefone, email, data_cadastro, ativo, cpf_cnpj FROM cliente WHERE id = ?";
        try{
            Connection conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    vo = new ClienteVO();
                    vo.setId(rs.getLong("id"));
                    vo.setNomeFantasia(rs.getString("nome_fantasia"));
                    vo.setRazaoSocial(rs.getString("razao_social"));
                    vo.setEnderecoId(rs.getLong("endereco_id"));
                    vo.setTelefone(rs.getString("telefone"));
                    vo.setEmail(rs.getString("email"));
                    vo.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                    vo.setAtivo(rs.getBoolean("ativo"));
                    vo.setCpfCnpj(rs.getString("cpf_cnpj"));
                    return vo;
                }
        } catch (SQLException e) 
        {
             e.printStackTrace(); 
        }
        return null;
    }

    public String adicionar(ClienteVO vo) {
        String sql = "INSERT INTO cliente (nome_fantasia, razao_social, endereco_id, telefone, email, data_cadastro, ativo, cpf_cnpj) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try{
            Connection conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getNomeFantasia());
            p.setString(2, vo.getRazaoSocial());
            if (vo.getEnderecoId() == null) 
                p.setNull(3, Types.BIGINT); 
            else 
                p.setLong(3, vo.getEnderecoId());

            p.setString(4, vo.getTelefone());
            p.setString(5, vo.getEmail());
            p.setTimestamp(6, Timestamp.valueOf(vo.getDataCadastro()));
            p.setBoolean(7, vo.getAtivo());
            p.setString(8, vo.getCpfCnpj());
            ResultSet rs = p.executeQuery();
            if (rs.next()) 
            { 
                vo.setId(rs.getLong("id")); 
                return "OK"; 
            } 
        } catch (SQLException e) 
        { 
            e.printStackTrace(); 
            return e.getMessage(); 
        }
        return "ERRO";
    }

    public String remover(long id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try
        {
            Connection conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setLong(1, id);
            int alterou = p.executeUpdate();
            return alterou > 0? "OK" : "NAO_ENCONTRADO";
        } catch (SQLException e) 
        { 
            e.printStackTrace(); 
            return e.getMessage(); 
        }
    }

    public String atualizar(ClienteVO vo) {
        String sql = "UPDATE cliente SET nome_fantasia = ?, razao_social = ?, endereco_id = ?, telefone = ?, email = ?, data_cadastro = ?, ativo = ?, cpf_cnpj = ? WHERE id = ?";
        try{
            Connection conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql);
            p.setString(1, vo.getNomeFantasia());
            p.setString(2, vo.getRazaoSocial());
            if (vo.getEnderecoId()==null) 
                p.setNull(3, Types.BIGINT); 
            else    
                p.setLong(3, vo.getEnderecoId());

            p.setString(4, vo.getTelefone());
            p.setString(5, vo.getEmail());
            p.setTimestamp(6, Timestamp.valueOf(vo.getDataCadastro()));
            p.setBoolean(7, vo.getAtivo());
            p.setString(8, vo.getCpfCnpj());
            p.setLong(9, vo.getId());
            int alterou = p.executeUpdate();
            return alterou > 0 ? "OK" : "NAO_ATUALIZADO";
        } catch (SQLException e) { e.printStackTrace(); return e.getMessage(); }
    }

    public ArrayList<ClienteVO> obterTodos() {
        ArrayList<ClienteVO> list = new ArrayList<>();
        String sql = "SELECT id, nome_fantasia, razao_social, endereco_id, telefone, email, data_cadastro, ativo, cpf_cnpj FROM cliente";
        try{ 
            Connection conexao = DriverManager.getConnection("","","");
            PreparedStatement p = conexao.prepareStatement(sql); 
            ResultSet rs = p.executeQuery();
            while (rs.next())
             {
                ClienteVO vo = new ClienteVO();
                vo.setId(rs.getLong("id"));
                vo.setNomeFantasia(rs.getString("nome_fantasia"));
                vo.setRazaoSocial(rs.getString("razao_social"));
                vo.setEnderecoId(rs.getLong("endereco_id"));
                vo.setTelefone(rs.getString("telefone"));
                vo.setEmail(rs.getString("email"));
                vo.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
                vo.setAtivo(rs.getBoolean("ativo"));
                vo.setCpfCnpj(rs.getString("cpf_cnpj"));
                list.add(vo);
            }
        } catch (SQLException e) 
        { 
            e.printStackTrace(); 
        }
        return list;
    }
}