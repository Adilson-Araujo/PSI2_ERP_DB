package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioProducaoDAO {

    // Lista áreas do associado logado
    public List<String> listarAreas(Long associadoId) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome FROM area WHERE ativo = TRUE AND associado_id = ? ORDER BY nome";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, associadoId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) lista.add(rs.getString("nome"));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // Retorna dados para a tabela: {Cultura, Kg, %, Custo}
    public List<Object[]> buscarDadosTabela(String nomeArea, Long associadoId) {
        List<Object[]> dados = new ArrayList<>();
        String sql = "SELECT e.nome, " +
                     "       SUM(CASE WHEN op.status = 'concluido' THEN op.quantidade_kg ELSE 0 END) as kg_concluido, " +
                     "       COUNT(CASE WHEN op.status = 'concluido' THEN 1 END) as qtd_concluido, " +
                     "       COUNT(*) as total " +
                     "FROM ordem_producao op " +
                     "JOIN especie e ON op.especie_id = e.id " +
                     "JOIN talhao t ON op.talhao_id = t.id " +
                     "JOIN area a ON t.area_id = a.id " +
                     "WHERE a.nome = ? AND a.associado_id = ? " +
                     "GROUP BY e.nome";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeArea);
            stmt.setLong(2, associadoId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                double kgConcluido = rs.getDouble("kg_concluido");
                int qtdConcluido = rs.getInt("qtd_concluido");
                int total = rs.getInt("total");
                double percentual = (total > 0) ? (qtdConcluido * 100.0 / total) : 0;
                
                dados.add(new Object[]{
                    rs.getString("nome"),
                    String.format("%.2f kg", kgConcluido),
                    String.format("%.0f%%", percentual)
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return dados;
    }
    
    // Retorna estatísticas da área para os cards
    public Map<String, Object> buscarEstatisticas(String nomeArea, Long associadoId) {
        Map<String, Object> stats = new HashMap<>();
        
        String sql = "SELECT " +
                     "  COUNT(CASE WHEN op.status = 'concluido' THEN 1 END) as ordens_concluidas, " +
                     "  SUM(CASE WHEN op.status = 'concluido' THEN op.quantidade_kg ELSE 0 END) as total_kg, " +
                     "  (SELECT COUNT(*) FROM registrar_problema rp " +
                     "   JOIN ordem_producao op2 ON rp.ordem_producao_id = op2.id " +
                     "   JOIN talhao t2 ON op2.talhao_id = t2.id " +
                     "   JOIN area a2 ON t2.area_id = a2.id " +
                     "   WHERE a2.nome = ? AND a2.associado_id = ?) as problemas " +
                     "FROM ordem_producao op " +
                     "JOIN talhao t ON op.talhao_id = t.id " +
                     "JOIN area a ON t.area_id = a.id " +
                     "WHERE a.nome = ? AND a.associado_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nomeArea);
            ps.setLong(2, associadoId);
            ps.setString(3, nomeArea);
            ps.setLong(4, associadoId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                stats.put("problemas", rs.getInt("problemas"));
                stats.put("ordens_concluidas", rs.getInt("ordens_concluidas"));
                stats.put("total_kg", rs.getDouble("total_kg"));
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
            stats.put("problemas", 0);
            stats.put("ordens_concluidas", 0);
            stats.put("total_kg", 0.0);
        }
        return stats;
    }
}