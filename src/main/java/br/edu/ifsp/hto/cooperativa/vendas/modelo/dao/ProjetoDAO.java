package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;

public class ProjetoDAO {

    public String adicionar(ProjetoVO projeto) {
        String sql = "INSERT INTO projeto (nome_projeto, data_criacao, data_final, orcamento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setTimestamp(2, Timestamp.valueOf(projeto.getDataCriacao()));
            
            if (projeto.getDataFinal() != null)
                stmt.setTimestamp(3, Timestamp.valueOf(projeto.getDataFinal()));
            else
                stmt.setNull(3, java.sql.Types.TIMESTAMP);

            stmt.setBigDecimal(4, projeto.getOrcamento());

            stmt.executeUpdate();
            return "Projeto salvo com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar: " + e.getMessage();
        }
    }

    public List<ProjetoVO> listarTodos() {
        return filtrarProjetos("");
    }

    // --- O MÉTODO QUE ESTÁ FALTANDO ---
    public List<ProjetoVO> filtrarProjetos(String termo) {
        List<ProjetoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto WHERE nome_projeto ILIKE ? ORDER BY nome_projeto";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + termo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProjetoVO p = new ProjetoVO();
                p.setId(rs.getLong("id"));
                p.setNomeProjeto(rs.getString("nome_projeto"));
                if(rs.getTimestamp("data_criacao") != null)
                    p.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                if(rs.getTimestamp("data_final") != null)
                    p.setDataFinal(rs.getTimestamp("data_final").toLocalDateTime());
                p.setOrcamento(rs.getBigDecimal("orcamento"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<ProjetoVO> filtrarAvancado(String termo, LocalDate dataInicio, LocalDate dataFim) {
        List<ProjetoVO> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM projeto WHERE 1=1 ");

        if (termo != null && !termo.trim().isEmpty()) {
            sql.append("AND nome_projeto ILIKE ? ");
        }

        if (dataInicio != null) {
            sql.append("AND data_criacao >= ? ");
        }

        if (dataFim != null) {
            sql.append("AND data_criacao <= ? ");
        }

        sql.append("ORDER BY nome_projeto");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (termo != null && !termo.trim().isEmpty()) {
                stmt.setString(index++, "%" + termo + "%");
            }

            if (dataInicio != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(dataInicio.atStartOfDay()));
            }

            if (dataFim != null) {
                // Ajusta para o final do dia (23:59:59)
                stmt.setTimestamp(index++, Timestamp.valueOf(dataFim.atTime(23, 59, 59)));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProjetoVO p = new ProjetoVO();
                p.setId(rs.getLong("id"));
                p.setNomeProjeto(rs.getString("nome_projeto"));
                if(rs.getTimestamp("data_criacao") != null)
                    p.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                if(rs.getTimestamp("data_final") != null)
                    p.setDataFinal(rs.getTimestamp("data_final").toLocalDateTime());
                p.setOrcamento(rs.getBigDecimal("orcamento"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}