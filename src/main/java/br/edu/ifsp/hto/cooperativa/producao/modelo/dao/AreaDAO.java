package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.Area;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    public List<Area> listarAreasPorAssociado(long associadoId) {

        List<Area> lista = new ArrayList<>();

        String sql = "SELECT id, nome FROM area " +
                     "WHERE associado_id = ? AND ativo = TRUE " +
                     "ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, associadoId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Area area = new Area(
                            rs.getLong("id"),
                            rs.getString("nome")
                    );

                    lista.add(area);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar áreas por associado:");
            e.printStackTrace();
        }

        return lista;
    }
}
