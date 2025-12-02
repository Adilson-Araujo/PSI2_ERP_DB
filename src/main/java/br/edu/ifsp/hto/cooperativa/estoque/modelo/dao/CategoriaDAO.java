package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.CategoriaVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaDAO {
    private static CategoriaDAO instancia = null;
    private static final Map<Integer, CategoriaVO> cache = new HashMap<>();
    
    private CategoriaDAO(){}
    public static CategoriaDAO getInstance(){
        if (instancia == null) instancia = new CategoriaDAO();
        return instancia;
    }
    
    private int nextId(){
        String sql = "SELECT MAX(id) FROM categoria";
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

    public boolean inserir(CategoriaVO categoria) {
        String sql = "INSERT INTO categoria (id, nome) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS);) {

            int idGerado = nextId();
            stmt.setInt(1, idGerado);
            stmt.setString(2, categoria.getNome());
            stmt.executeUpdate();
                        
            categoria.setId(idGerado);
            cache.put(idGerado, categoria);
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria: " + e.getMessage());
            return false;
        }
    }

    public CategoriaVO buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome FROM categoria WHERE id = ?";
        CategoriaVO categoria = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new CategoriaVO(
                        rs.getInt("id"),
                        rs.getString("nome"));
                cache.put(id, categoria);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria por ID: " + e.getMessage());
        }

        return categoria;
    }

    public boolean atualizar(CategoriaVO categoria) {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "UPDATE categoria SET deletado = true WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir categoria: " + e.getMessage());
            return false;
        }
    }

    public List<CategoriaVO> listarTodas() {
        List<CategoriaVO> categorias = new ArrayList<>();
        String sql = "SELECT id, nome FROM categoria WHERE deletado = false ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                if (!cache.containsKey(id)) {
                    CategoriaVO categoria = new CategoriaVO(
                        id,
                        rs.getString("nome")
                    );
                    cache.put(id, categoria);
                }
                categorias.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }

        return categorias;
    }
}
