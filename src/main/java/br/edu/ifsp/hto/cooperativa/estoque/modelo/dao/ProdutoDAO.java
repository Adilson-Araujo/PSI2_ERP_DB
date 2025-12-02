package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.ProdutoPrecificadoTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.ProdutoVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EspecieVO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.PrecoPPAVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDAO {
    private static ProdutoDAO instancia = null;
    private static final EspecieDAO DAO_especie = EspecieDAO.getInstance();
    private static final PrecoPPADAO DAO_preco_ppa = PrecoPPADAO.getInstance();
    private static final Map<Integer, ProdutoVO> cache = new HashMap<>();
    
    private ProdutoDAO(){}
    public static ProdutoDAO getInstance(){
        if (instancia == null) instancia = new ProdutoDAO();
        return instancia;
    }
    
    private int nextId(){
        String sql = "SELECT MAX(id) FROM Produto";
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
    
    public boolean inserir(ProdutoVO produto) {
        String sql = "INSERT INTO produto (id, especie_id, nome, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int idGerado = nextId();
            stmt.setInt(1, idGerado);
            stmt.setInt(2, produto.getEspecie().getId());
            stmt.setString(3, produto.getNome());
            stmt.setString(4, produto.getDescricao());
            stmt.executeUpdate();
            
            produto.setId(idGerado);
            cache.put(idGerado, produto);
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public ProdutoVO buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, especie_id, nome, descricao FROM produto WHERE id = ?";
        ProdutoVO produto = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                EspecieVO especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                
                produto = new ProdutoVO(
                        rs.getInt("id"),
                        especie,
                        rs.getString("nome"),
                        rs.getString("descricao"));
                cache.put(id, produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
            System.out.println("ERRO AQUI");
        }

        return produto;
    }

    public boolean atualizar(ProdutoVO produto) {
        String sql = "UPDATE produto SET especie_id = ?, nome = ?, descricao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getEspecie().getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Produtos: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "UPDATE produto SET deletado = TRUE WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }

    public List<ProdutoVO> listarTodos() {
        List<ProdutoVO> produtos = new ArrayList<>();
        String sql = "SELECT id, especie_id, nome, descricao FROM produto WHERE deletado = false";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                if (!cache.containsKey(id)) {
                    EspecieVO especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                    ProdutoVO produto = new ProdutoVO(
                            id,
                            especie,
                            rs.getString("nome"),
                            rs.getString("descricao"));
                    cache.put(id, produto);
                }
                produtos.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }
    
    // Outros para alem do CRUD básico.
    
    public ProdutoVO buscarPorEspecieId(int especie_id) {
        String sql = "SELECT id, especie_id, nome, descricao FROM produto WHERE especie_id = ?";
        ProdutoVO produto = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                if (cache.containsKey(id)) {
                    return cache.get(id);
                }
                
                EspecieVO especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                
                produto = new ProdutoVO(
                        id,
                        especie,
                        rs.getString("nome"),
                        rs.getString("descricao"));
                cache.put(id, produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        }

        return produto;
    }
    
    public ProdutoPrecificadoTO buscarPrecificadoPorEspecieId(int id, Timestamp data) {
        String sql = """
                     SELECT 
                         p.id AS produto_id,
                         p.nome AS produto_nome,
                         p.descricao AS produto_descricao,
                         p.deletado AS produto_deletado,
                     
                         e.id AS especie_id,
                         e.nome AS especie_nome,
                         e.descricao AS especie_descricao,
                         e.tempo_colheita,
                         e.rendimento_kg_m2,
                         e.deletado AS especie_deletado,
                     
                         pr.id AS preco_id,
                         pr.valor AS preco_valor,
                         pr.data_inicio,
                         pr.data_final
                     
                     FROM produto p
                     JOIN especie e
                         ON e.id = p.especie_id
                     LEFT JOIN preco_ppa pr
                         ON pr.especie_id = e.id
                         AND pr.data_inicio <= ?
                         AND (pr.data_final IS NULL OR pr.data_final >= ?)
                     
                     WHERE e.id = ?""";
        ProdutoPrecificadoTO produtoPrecificado = null;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, data);
            stmt.setTimestamp(2, data);
            stmt.setInt(3, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ProdutoVO produto;
                EspecieVO especie;
                PrecoPPAVO preco_ppa;
                
                int esp_id = rs.getInt("especie_id");
                especie = DAO_especie.buscarPorId(esp_id);
                preco_ppa = DAO_preco_ppa.buscarPorId(data, esp_id);
                
                int pdt_id = rs.getInt("produto_id");
                if (cache.containsKey(pdt_id)){
                    produto = cache.get(pdt_id);
                } else {
                    produto = new ProdutoVO(
                        pdt_id,
                        especie,
                        rs.getString("produto_nome"),
                        rs.getString("produto_descricao"));
                }
                
                produtoPrecificado = new ProdutoPrecificadoTO(produto, especie, preco_ppa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
            System.out.println("ERRO AQUI");
        }

        return produtoPrecificado;
    }
    
    public List<ProdutoPrecificadoTO> listarTodosPrecificados(Timestamp data) {
        List<ProdutoPrecificadoTO> produtosPrecificados = new ArrayList<>();
        String sql = """
                     SELECT 
                         p.id AS produto_id,
                         p.nome AS produto_nome,
                         p.descricao AS produto_descricao,
                         p.deletado AS produto_deletado,
                     
                         e.id AS especie_id,
                         e.nome AS especie_nome,
                         e.descricao AS especie_descricao,
                         e.tempo_colheita,
                         e.rendimento_kg_m2,
                         e.deletado AS especie_deletado,
                     
                         pr.id AS preco_id,
                         pr.valor AS preco_valor,
                         pr.data_inicio,
                         pr.data_final
                     
                     FROM produto p
                     JOIN especie e
                         ON e.id = p.especie_id
                     LEFT JOIN preco_ppa pr
                         ON pr.especie_id = e.id
                         AND pr.data_inicio <= ?
                         AND (pr.data_final IS NULL OR pr.data_final >= ?)
                    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setTimestamp(1, data);
            stmt.setTimestamp(2, data);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {                
                ProdutoVO produto;
                EspecieVO especie;
                PrecoPPAVO preco_ppa;
                
                int esp_id = rs.getInt("especie_id");
                especie = DAO_especie.buscarPorId(esp_id);
                preco_ppa = DAO_preco_ppa.buscarPorId(data, esp_id);
                
                int pdt_id = rs.getInt("produto_id");
                if (cache.containsKey(pdt_id)){
                    produto = cache.get(pdt_id);
                } else {
                    produto = new ProdutoVO(
                        pdt_id,
                        especie,
                        rs.getString("produto_nome"),
                        rs.getString("produto_descricao"));
                    cache.put(pdt_id, produto);
                }
                
                produtosPrecificados.add(new ProdutoPrecificadoTO(produto, especie, preco_ppa));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtosPrecificados;
    }
}
