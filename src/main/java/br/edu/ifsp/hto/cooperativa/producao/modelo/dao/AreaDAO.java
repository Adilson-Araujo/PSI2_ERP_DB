package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.AreaVO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    /**
     * Método auxiliar para mapear o ResultSet para o AreaVO, carregando todos os campos.
     */
    private AreaVO mapearArea(ResultSet rs) throws SQLException {
        AreaVO area = new AreaVO();
        area.setId(rs.getLong("id"));
        area.setAssociadoId(rs.getLong("associado_id"));
        area.setNome(rs.getString("nome"));
        
        // 🔑 MUDANÇA AQUI: Troque getBigDecimal() por getDouble()
        area.setAreaTotal(rs.getDouble("area_total")); 
        area.setAreaUtilizada(rs.getDouble("area_utilizada")); 
        area.setPh(rs.getDouble("ph"));
        
        return area;
    }
    
    // --- MÉTODOS REQUERIDOS PELO GerenciarAreaController ---

    /**
     * 🔑 1. Implementação/Correção de buscarPorAssociadoId (antigo listarAreasPorAssociado).
     * Carrega TODAS as colunas para o AreaVO e propaga SQLException.
     */
    public List<AreaVO> buscarPorAssociadoId(long associadoId) throws SQLException { // Propaga SQLException
        
        List<AreaVO> lista = new ArrayList<>();

        String sql = """
            SELECT 
                id, associado_id, nome, area_total, area_utilizada, ph 
                -- Inclua 'ativo' se for necessário no VO
            FROM area 
            WHERE associado_id = ?
            -- Seu código original incluía 'AND ativo = TRUE', mantivemos a condição:
            -- WHERE associado_id = ? AND ativo = TRUE 
            ORDER BY nome
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, associadoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // 🔑 Usa o método auxiliar para carregar todos os campos
                    lista.add(mapearArea(rs)); 
                }
            }
        } // O bloco try-with-resources já fecha a conexão e o PreparedStatement
        
        return lista;
    }
    
    /**
     * 🔑 2. Novo Método: Implementação de buscarPorId(Long id).
     * Usado para carregar os detalhes completos da área antes de buscar Talhões/Canteiros.
     */
    public AreaVO buscarPorId(Long id) throws SQLException {
        String sql = """
            SELECT 
                id, associado_id, nome, area_total, area_utilizada, ph 
                -- Inclua 'ativo' se for necessário no VO
            FROM area
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 🔑 Usa o método auxiliar para carregar todos os campos
                    return mapearArea(rs); 
                }
            }
        }
        return null;
    }

    public BigDecimal calcularEAtualizarAreaUtilizada(Long areaId) throws SQLException {
        
        // 1. Consulta SQL para SOMAR a área dos talhões.
        String sqlSelect = """
            SELECT SUM(t.area_talhao) AS area_utilizada_calculada
            FROM talhao t
            WHERE t.Area_id = ?
        """;

        BigDecimal areaUtilizadaCalculada = BigDecimal.ZERO;
        
        // Bloco 1: Calcular a soma das áreas dos talhões
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {

            psSelect.setLong(1, areaId);

            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    // Pega o resultado da soma. Se não houver talhões, retorna 0.
                    areaUtilizadaCalculada = rs.getBigDecimal("area_utilizada_calculada");
                    if (areaUtilizadaCalculada == null) {
                        areaUtilizadaCalculada = BigDecimal.ZERO;
                    }
                }
            }
        }
        
        // 2. Consulta SQL para ATUALIZAR o campo area_utilizada na tabela Area.
        String sqlUpdate = """
            UPDATE area
            SET area_utilizada = ?
            WHERE id = ?
        """;
        
        // Bloco 2: Atualizar a tabela Area
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
            
            psUpdate.setBigDecimal(1, areaUtilizadaCalculada);
            psUpdate.setLong(2, areaId);
            psUpdate.executeUpdate();
            
        } 
        
        return areaUtilizadaCalculada;
    }

    // --- MÉTODOS DE MANIPULAÇÃO DE DADOS (Exemplo de Inserir) ---
    
    // public void inserir(AreaVO area) throws SQLException {
    //    // ... sua lógica de INSERT
    // }
    
    // ... Outros métodos de CRUD
}