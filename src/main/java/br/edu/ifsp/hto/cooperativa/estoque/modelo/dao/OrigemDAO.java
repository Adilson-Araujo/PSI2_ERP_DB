package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.OrigemVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrigemDAO {
    private static OrigemDAO instancia = null;
    private static final Map<Integer, OrigemVO> cache = new HashMap<>();
    
    private OrigemDAO(){}
    public static OrigemDAO getInstance(){
        if (instancia == null) instancia = new OrigemDAO();
        return instancia;
    }

    public OrigemVO buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome FROM origem WHERE id = ?";
        OrigemVO origem = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                origem = new OrigemVO(rs.getInt("id"), rs.getString("nome"));
                cache.put(id, origem);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar origem por ID: " + e.getMessage());
        }

        return origem;
    }

    public List<OrigemVO> listarTodas() {
        List<OrigemVO> origens = new ArrayList<>();
        String sql = "SELECT id, nome FROM origem ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                if (!cache.containsKey(id)) {
                    OrigemVO origem = new OrigemVO(
                        id,
                        rs.getString("nome")
                    );
                    cache.put(id, origem);
                }
                origens.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar origens: " + e.getMessage());
        }

        return origens;
    }
}
