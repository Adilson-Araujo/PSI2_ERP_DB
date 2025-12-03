package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.CulturaDTO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dto.TipoProblemaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrarProblemasDAO {

    /**
     * Lista todos os tipos de problemas cadastrados
     */
    public List<TipoProblemaDTO> listarTipos() {
        List<TipoProblemaDTO> lista = new ArrayList<>();
        String sql = "SELECT id, descricao FROM tipo_problema ORDER BY descricao";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new TipoProblemaDTO(
                    rs.getLong("id"),
                    rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    /**
     * Lista todas as culturas ativas (ordens de produção em execução) do associado logado
     */
    public List<CulturaDTO> listarCulturasAtivas(Long associadoId) {
        List<CulturaDTO> lista = new ArrayList<>();
        String sql = "SELECT op.id, e.nome, op.data_inicio, op.data_fim " +
                     "FROM ordem_producao op " +
                     "JOIN especie e ON op.especie_id = e.id " +
                     "JOIN talhao t ON op.talhao_id = t.id " +
                     "JOIN area a ON t.area_id = a.id " +
                     "WHERE op.status = 'em_execucao' " +
                     "AND a.associado_id = ? " +
                     "ORDER BY op.data_inicio DESC";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, associadoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new CulturaDTO(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getTimestamp("data_inicio"),
                        rs.getTimestamp("data_fim")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }

    /**
     * Salva um registro de problema no banco
     */
    public boolean salvar(Long ordemProducaoId, Long tipoProblemaId, int quantidade, java.util.Date data, String observacoes) {
        String sql = "INSERT INTO registrar_problema (ordem_producao_id, tipo_problema_id, quantidade_afetada, data_problema, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, ordemProducaoId);
            ps.setLong(2, tipoProblemaId);
            ps.setInt(3, quantidade);
            ps.setTimestamp(4, new Timestamp(data.getTime()));
            ps.setString(5, observacoes);
            
            int linhas = ps.executeUpdate();
            return linhas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
