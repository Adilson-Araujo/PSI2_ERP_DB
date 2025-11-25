package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Especie;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPA;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrecoPPADAO {
    private static PrecoPPADAO instancia = null;
    private static final EspecieDAO DAO_especie = EspecieDAO.getInstance();
    private static final Map<String, PrecoPPA> cache = new HashMap<>();
    
    private PrecoPPADAO(){}
    public static PrecoPPADAO getInstance(){
        if (instancia == null) instancia = new PrecoPPADAO();
        return instancia;
    }
    
    public boolean inserir(PrecoPPA precoppa) {
        String sql = "INSERT INTO preco_ppa (data_inicio, especie_id, data_final, valor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp data_inicio = precoppa.getData_inicio();
            int especie_id = precoppa.getEspecie().getId();
            
            stmt.setTimestamp(1, data_inicio);
            stmt.setInt(2, especie_id);
            stmt.setTimestamp(3, precoppa.getData_final());
            stmt.setFloat(4, precoppa.getValor());
            stmt.executeUpdate();
            
            cache.put(data_inicio+" "+especie_id, precoppa);
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir preço ppa: " + e.getMessage());
            return false;
        }
    }

    public PrecoPPA buscarPorId(Timestamp data, int especie_id) {
        String chave = data+" "+especie_id;
        if (cache.containsKey(chave)) {
            return cache.get(chave);
        }
        
        /*
         * buscarPorId procurar pelo preço vigente de uma especie
         * em uma data definida.
         */
        String sql = "SELECT data_inicio, especie_id, data_final, valor FROM preco_ppa WHERE especie_id = ? AND data_inicio <= ? AND data_final >= ? ORDER BY data_inicio DESC";
        PrecoPPA precoppa = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie_id);
            stmt.setTimestamp(2, data);
            stmt.setTimestamp(3, data);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Especie especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                
                precoppa = new PrecoPPA(
                        rs.getTimestamp("data_inicio"),
                        especie,
                        rs.getTimestamp("data_final"),
                        rs.getFloat("valor"));
                cache.put(chave, precoppa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar preço ppa por ID: " + e.getMessage());
        }

        return precoppa;
    }

    public boolean atualizar(PrecoPPA precoppa) {
        String sql = "UPDATE preco_ppa SET data_final = ?, valor = ? WHERE especie_id = ? AND data_inicio = ? ORDER BY data_inicio DESC LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(3, precoppa.getData_final());
            stmt.setFloat(4, precoppa.getValor());
            
            stmt.setInt(5, precoppa.getEspecie().getId());
            stmt.setTimestamp(6, precoppa.getData_inicio());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar preço ppa: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int especie_id, Timestamp data_inicio) {
        String sql = "DELETE FROM preco_ppa WHERE especie_id = ? AND data_inicio = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie_id);
            stmt.setTimestamp(2, data_inicio);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir preço ppa: " + e.getMessage());
            return false;
        }
    }

    public List<PrecoPPA> listarTodos() {
        List<PrecoPPA> precosppa = new ArrayList<>();
        String sql = "SELECT data_inicio, especie_id, data_final, valor FROM precoppa";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Timestamp data_inicio = rs.getTimestamp("data_inicio");
                int especie_id = rs.getInt("especie_id");
                String chave = data_inicio+" "+especie_id;
                
                if (!cache.containsKey(chave)) {
                    Especie especie = DAO_especie.buscarPorId(especie_id);
                
                    PrecoPPA precoppa = new PrecoPPA(
                        data_inicio,
                        especie,
                        rs.getTimestamp("data_fim"),
                        rs.getFloat("valor"));
                    cache.put(chave, precoppa);
                }
                precosppa.add(cache.get(chave));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Precos PPA: " + e.getMessage());
        }

        return precosppa;
    }
}
