package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ArmazemVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmazemDAO {
    private static ArmazemDAO instancia = null;
    private static final Map<Integer, ArmazemVO> cache = new HashMap<>();
    
    private ArmazemDAO(){}
    public static ArmazemDAO getInstance(){
        if (instancia == null) instancia = new ArmazemDAO();
        return instancia;
    }
    
    private int nextId(){
        String sql = "SELECT MAX(id) FROM armazem";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1)+1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean inserir(ArmazemVO armazem) {
        String sql = "INSERT INTO armazem (id, nome, endereco_id) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS);) {

            int idGerado = nextId();
            stmt.setInt(1, idGerado);
            stmt.setString(2, armazem.getNome());
            stmt.setInt(3, armazem.getEnderecoId());
            stmt.executeUpdate();
            
            armazem.setId(idGerado);
            cache.put(idGerado, armazem);
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir armazem: " + e.getMessage());
            return false;
        }
    }

    public ArmazemVO buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome, endereco_id FROM armazem WHERE id = ?";
        ArmazemVO armazem = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                armazem = new ArmazemVO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("endereco_id"));
                cache.put(id, armazem);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar armazem por ID: " + e.getMessage());
        }

        return armazem;
    }

    public boolean atualizar(ArmazemVO armazem) {
        String sql = "UPDATE armazem SET nome = ?, endereco_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, armazem.getNome());
            stmt.setInt(2, armazem.getEnderecoId());
            stmt.setInt(3, armazem.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar armazem: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "UPDATE armazem SET deletado = true WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir armazem: " + e.getMessage());
            return false;
        }
    }

    public List<ArmazemVO> listarTodos() {
        List<ArmazemVO> armazens = new ArrayList<>();
        String sql = "SELECT id, nome, endereco_id FROM armazem WHERE deletado = false";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                if (!cache.containsKey(id)) {
                    ArmazemVO armazem = new ArmazemVO(
                        id,
                        rs.getString("nome"),
                        rs.getInt("endereco_id")
                    );
                    cache.put(id, armazem);
                }
                armazens.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar armazens: " + e.getMessage());
        }

        return armazens;
    }
}
