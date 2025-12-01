package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.TipoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoDAO {
    private static TipoDAO instancia = null;
    private static final Map<Integer, TipoVO> cache = new HashMap<>();
    
    private TipoDAO(){}
    public static TipoDAO getInstance(){
        if (instancia == null) instancia = new TipoDAO();
        return instancia;
    }

    public TipoVO buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome FROM tipo WHERE id = ?";
        TipoVO tipo = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new TipoVO(rs.getInt("id"), rs.getString("nome"));
                cache.put(id, tipo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo por ID: " + e.getMessage());
        }

        return tipo;
    }

    public List<TipoVO> listarTodos() {
        List<TipoVO> tipos = new ArrayList<>();
        String sql = "SELECT id, nome FROM tipo ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                if (!cache.containsKey(id)) {
                    TipoVO tipo = new TipoVO(
                        id,
                        rs.getString("nome")
                    );
                    cache.put(id, tipo);
                }
                tipos.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar origens: " + e.getMessage());
        }

        return tipos;
    }
}
