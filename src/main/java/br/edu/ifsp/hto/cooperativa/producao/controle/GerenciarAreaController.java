package br.edu.ifsp.hto.cooperativa.producao.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.hto.cooperativa.producao.modelo.dao.AreaDAO;
import br.edu.ifsp.hto.cooperativa.producao.modelo.Area;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory; // ajusta o pacote conforme o seu projeto

public class GerenciarAreaController {

    private AreaDAO areaDAO;

    public GerenciarAreaController() {
        this.areaDAO = new AreaDAO();
    }

    // Método para carregar UMA ÁREA COMPLETA pelo ID (útil para a TelaTalhao)
    public Area carregarAreaCompletaPorId(long areaId) {
        Area area = null;
        
        // Agora busca todos os campos da tabela AREA
        String sql = "SELECT id, nome, area_total, area_utilizada, ph FROM area WHERE id = ?"; 

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, areaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long id = rs.getLong("id"); 
                String nome = rs.getString("nome");
                // Puxando os valores DECIMAL/DOUBLE do banco
                double areaTotal = rs.getDouble("area_total");
                double areaUtilizada = rs.getDouble("area_utilizada");
                double ph = rs.getDouble("ph");

                // Usando o construtor COMPLETO da classe Area
                area = new Area(id, nome, areaTotal, areaUtilizada, ph);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar área completa: " + e.getMessage());
        }

        return area;
    }

    public List<Area> carregarAreas(long associadoId) {
        List<Area> lista = new ArrayList<>();

        String sql = "SELECT id, nome FROM area WHERE associado_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, associadoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                lista.add(new Area(id, nome));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
