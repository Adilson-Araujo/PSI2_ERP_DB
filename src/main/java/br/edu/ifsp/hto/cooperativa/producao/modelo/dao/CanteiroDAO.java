package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.CanteiroVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanteiroDAO {

    /**
     * Busca todos os canteiros (com base na Ordem de Produção) associados a um Talhão (Talhao_id).
     * Nota: O esquema de Canteiro usa ordem_producao_id, que está ligada ao Talhao_id via tabela 'plano'.
     */
    public List<CanteiroVO> buscarPorTalhaoId(Long talhaoId) throws SQLException {
        String sql = """
            SELECT 
                c.id, 
                c.ordem_producao_id, 
                c.nome, 
                c.area_canteiro_m2, 
                c.observacoes, 
                c.kg_gerados, 
                c.status
            FROM canteiro c
            JOIN plano p ON c.ordem_producao_id = p.id -- Assumindo que ordem_producao_id em canteiro é o id do plano
            WHERE p.Talhao_id = ?
            ORDER BY c.nome
        """;

        List<CanteiroVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, talhaoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CanteiroVO canteiro = new CanteiroVO();
                    canteiro.setId(rs.getLong("id"));
                    canteiro.setOrdemProducaoId(rs.getLong("ordem_producao_id"));
                    canteiro.setNome(rs.getString("nome"));
                    canteiro.setAreaCanteiroM2(rs.getBigDecimal("area_canteiro_m2"));
                    canteiro.setObservacoes(rs.getString("observacoes"));
                    canteiro.setKgGerados(rs.getBigDecimal("kg_gerados"));
                    canteiro.setStatus(rs.getString("status"));
                    lista.add(canteiro);
                }
            }
        }
        return lista;
    }

    // Implemente inserir, atualizar, remover conforme a sua necessidade...
}