package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

public class AssociadoDAO {

    public static class Associado {
        private Long id;
        private String nomeFantasia;

        public Associado(Long id, String nomeFantasia) {
            this.id = id;
            this.nomeFantasia = nomeFantasia;
        }

        public Long getId() { return id; }
        public String getNomeFantasia() { return nomeFantasia; }

        @Override
        public String toString() {
            return nomeFantasia; 
        }
    }

    // --- NOVO MÉTODO: BUSCA POR ID (Implementação) ---
    public Associado buscarPorId(Long associadoId) {
        String sql = "SELECT id, nome_fantasia FROM associado WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, associadoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Associado(
                        rs.getLong("id"),
                        rs.getString("nome_fantasia")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Associado> listarTodos() {
        List<Associado> lista = new ArrayList<>();
        String sql = "SELECT id, nome_fantasia FROM associado WHERE ativo = true ORDER BY nome_fantasia";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Associado(
                    rs.getLong("id"),
                    rs.getString("nome_fantasia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}