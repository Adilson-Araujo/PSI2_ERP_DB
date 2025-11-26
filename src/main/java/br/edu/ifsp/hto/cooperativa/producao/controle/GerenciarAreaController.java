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
