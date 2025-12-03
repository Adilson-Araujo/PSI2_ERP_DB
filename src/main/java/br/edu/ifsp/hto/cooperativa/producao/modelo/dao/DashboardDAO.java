package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.DashboardVO;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DashboardDAO {

    public DashboardVO carregarDadosIniciais(long associadoId) {
        DashboardVO vo = new DashboardVO();
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            // 1. Contar Ordens de Produção em Execução
            String sqlOrdens = "SELECT COUNT(op.id) FROM ordem_producao op " +
                               "JOIN plano p ON op.plano_id = p.id " +
                               "JOIN talhao t ON p.Talhao_id = t.id " +
                               "JOIN area a ON t.Area_id = a.id " +
                               "WHERE a.associado_id = ? AND op.status = 'em_execucao'";
            try (PreparedStatement ps = conn.prepareStatement(sqlOrdens)) {
                ps.setLong(1, associadoId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) vo.setQtdTalhoes(rs.getInt(1));
            }

            // 2. Contar Canteiros Ativos (Status 'crescendo' ou ativo=true)
            String sqlCanteiros = "SELECT COUNT(c.id) FROM canteiro c " +
                                  "JOIN ordem_producao op ON c.ordem_producao_id = op.id " +
                                  "JOIN plano p ON op.plano_id = p.id " +
                                  "JOIN talhao t ON p.Talhao_id = t.id " +
                                  "JOIN area a ON t.Area_id = a.id " +
                                  "WHERE a.associado_id = ? AND c.ativo = TRUE";
            try (PreparedStatement ps = conn.prepareStatement(sqlCanteiros)) {
                ps.setLong(1, associadoId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) vo.setQtdCanteiros(rs.getInt(1));
            }

            // 3. Contar Problemas Ativos (Todos registrados para o associado)
            String sqlProblemas = "SELECT COUNT(rp.id) FROM registrar_problema rp " +
                                  "JOIN ordem_producao op ON rp.ordem_producao_id = op.id " +
                                  "JOIN plano p ON op.plano_id = p.id " +
                                  "JOIN talhao t ON p.Talhao_id = t.id " +
                                  "JOIN area a ON t.Area_id = a.id " +
                                  "WHERE a.associado_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlProblemas)) {
                ps.setLong(1, associadoId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) vo.setQtdProblemas(rs.getInt(1));
            }

            // 4. Listar Últimas Movimentações de Entrada do Associado (produtos enviados ao estoque)
            // Filtra por tipo_id = 1 (ENTRADA/PRODUCAO) se existir essa convenção
            String sqlMovimentacoes = "SELECT e.nome as nome_especie, e.descricao, m.data_movimento, m.quantidade " +
                                      "FROM movimentacao m " +
                                      "JOIN produto p ON m.produto_id = p.id " +
                                      "JOIN especie e ON p.especie_id = e.id " +
                                      "WHERE m.associado_id = ? " +
                                      "AND m.deletado = FALSE " +
                                      "AND m.tipo_id = 1 " +
                                      "ORDER BY m.data_movimento DESC LIMIT 10";

            try (PreparedStatement ps = conn.prepareStatement(sqlMovimentacoes)) {
                ps.setLong(1, associadoId);
                ResultSet rs = ps.executeQuery();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                
                while (rs.next()) {
                    String nomeEspecie = rs.getString("nome_especie");
                    String descricao = rs.getString("descricao");
                    Timestamp dataMovimento = rs.getTimestamp("data_movimento");
                    String dataStr = (dataMovimento != null) ? sdf.format(dataMovimento) : "--";
                    double quantidadeKg = rs.getDouble("quantidade");
                    
                    // Usa o nome da espécie como "nome", quantidade como "custo", data como "dataFinal" e descrição como "prioridade"
                    vo.getAtividadesPendentes().add(
                        new DashboardVO.AtividadeResumoVO(nomeEspecie, quantidadeKg, dataStr, descricao != null ? descricao : "-")
                    );
                }
                
                // Se quiser o count exato do banco em vez do limit 10, teria que fazer outra query,
                // mas para o dashboard, usar o tamanho da lista ou um count separado serve.
                // Vamos usar o count separado para o card:
                vo.setQtdAtividades(contarOrdensConcluidas(conn, associadoId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vo;
    }

    private int contarOrdensConcluidas(Connection conn, long associadoId) throws SQLException {
        // Contar ordens sem filtro de status para verificar total
        String sqlTotal = "SELECT COUNT(op.id), " +
                         "SUM(CASE WHEN op.status = 'concluido' THEN 1 ELSE 0 END) as concluidas, " +
                         "SUM(CASE WHEN op.status = 'em_execucao' THEN 1 ELSE 0 END) as em_execucao " +
                         "FROM ordem_producao op " +
                         "JOIN plano p ON op.plano_id = p.id " +
                         "JOIN talhao t ON p.Talhao_id = t.id " +
                         "JOIN area a ON t.Area_id = a.id " +
                         "WHERE a.associado_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sqlTotal)) {
            ps.setLong(1, associadoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("=== DEBUG Ordens do Associado " + associadoId + " ===");
                System.out.println("Total de ordens: " + rs.getInt(1));
                System.out.println("Ordens concluídas: " + rs.getInt("concluidas"));
                System.out.println("Ordens em execução: " + rs.getInt("em_execucao"));
                return rs.getInt("concluidas");
            }
        }
        return 0;
    }
}