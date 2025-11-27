package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.to.EstoqueTO;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Armazem;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.EstoqueAtual;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueAtualDAO {
    private static EstoqueAtualDAO instancia = null;
    private static final ProdutoDAO DAO_produto = ProdutoDAO.getInstance();
    private static final ArmazemDAO DAO_armazem = ArmazemDAO.getInstance();
    private static final Map<String, EstoqueAtual> cache = new HashMap<>();
    
    private EstoqueAtualDAO(){}
    public static EstoqueAtualDAO getInstance(){
        if (instancia == null) instancia = new EstoqueAtualDAO();
        return instancia;
    }
    
    public boolean inserir(EstoqueAtual estoqueAtual) {
        String sql = "INSERT INTO estoque_atual (associado_id, produto_id, armazem_id, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int associado_id = estoqueAtual.getAssociadoId();
            int produto_id = estoqueAtual.getProduto().getId();
            int armazem_id = estoqueAtual.getArmazem().getId();
            String chave = associado_id+" "+produto_id+" "+armazem_id;
            
            stmt.setInt(1, associado_id);
            stmt.setInt(2, produto_id);
            stmt.setInt(3, armazem_id);
            stmt.setFloat(4, estoqueAtual.getQuantidade());
            stmt.executeUpdate();
            
            cache.put(chave, estoqueAtual);
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir estoque atual: " + e.getMessage());
            return false;
        }
    }

    public EstoqueAtual buscarPorId(int associado_id, int produto_id, int armazem_id) {
        String chave = associado_id+" "+produto_id+" "+armazem_id;
        if (cache.containsKey(chave)) {
            return cache.get(chave);
        }
        
        String sql = "SELECT associado_id, produto_id, armazem_id, quantidade FROM estoque_atual WHERE associado_id = ? AND produto_id = ? AND armazem_id = ?";
        EstoqueAtual estoqueAtual = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, associado_id);
            stmt.setInt(2, produto_id);
            stmt.setInt(3, armazem_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = DAO_produto.buscarPorId(rs.getInt("produto_id"));
                Armazem armazem = DAO_armazem.buscarPorId(rs.getInt("armazem_id"));
                
                estoqueAtual = new EstoqueAtual(
                        rs.getInt("associado_id"),
                        produto,
                        armazem,
                        rs.getFloat("quantidade"));
                cache.put(chave, estoqueAtual);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar estoque atual por ID: " + e.getMessage());
        }

        return estoqueAtual;
    }
    
    public boolean atualizar(EstoqueAtual estoqueAtual) {
        String sql = "UPDATE estoque_atual SET quantidade = ? WHERE associado_id = ? AND produto_id = ? AND armazem_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, estoqueAtual.getQuantidade());
            stmt.setInt(2, estoqueAtual.getAssociadoId());
            stmt.setInt(3, estoqueAtual.getProduto().getId());
            stmt.setInt(4, estoqueAtual.getArmazem().getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque atual: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int associado_id, int produto_id, int armazem_id) {
        String sql = "DELETE FROM estoque_atual WHERE associado_id = ? AND produto_id = ? AND armazem_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, associado_id);
            stmt.setInt(2, produto_id);
            stmt.setInt(3, armazem_id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir estoque atual: " + e.getMessage());
            return false;
        }
    }

    public List<EstoqueAtual> listarTodos() {
        List<EstoqueAtual> estoquesAtuais = new ArrayList<>();
        String sql = "SELECT associado_id, produto_id, armazem_id, quantidade FROM estoque_atual";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int associado_id = rs.getInt("associado_id");
                int produto_id = rs.getInt("produto_id");
                int armazem_id = rs.getInt("armazem_id");
                String chave = associado_id+" "+produto_id+" "+armazem_id;
                
                if (!cache.containsKey(chave)) {
                    Produto produto = DAO_produto.buscarPorId(produto_id);
                    Armazem armazem = DAO_armazem.buscarPorId(armazem_id);

                    EstoqueAtual estoqueAtual = new EstoqueAtual(
                        associado_id,
                        produto,
                        armazem,
                        rs.getFloat("quantidade"));
                    cache.put(chave, estoqueAtual);
                }
                estoquesAtuais.add(cache.get(chave));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar estoques atuais: " + e.getMessage());
        }

        return estoquesAtuais;
    }
    
    // Outros para alem do crud básico

    public List<EstoqueTO> listarEstoque(int associado_id) {
        List<EstoqueTO> estoquesAtuais = new ArrayList<>();
        String sql = "SELECT associado_id, produto_id, SUM(quantidade) AS quantidade FROM estoque_atual WHERE associado_id = ? GROUP BY associado_id, produto_id;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, associado_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int produto_id = rs.getInt("produto_id");
                Produto produto = DAO_produto.buscarPorId(produto_id);

                EstoqueTO estoqueAtual = new EstoqueTO(
                    associado_id,
                    produto,
                    rs.getFloat("quantidade"));
                estoquesAtuais.add(estoqueAtual);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar estoques atuais: " + e.getMessage());
        }

        return estoquesAtuais;
    }
    
    public EstoqueTO buscarEstoque(int associado_id, int produto_id) {
        String sql = "SELECT associado_id, produto_id, SUM(quantidade) AS quantidade FROM estoque_atual WHERE associado_id = ? AND produto_id = ? GROUP BY associado_id, produto_id;";
        EstoqueTO estoqueAtual = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, associado_id);
            stmt.setInt(2, produto_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = DAO_produto.buscarPorId(produto_id);
                
                estoqueAtual = new EstoqueTO(
                        associado_id,
                        produto,
                        rs.getFloat("quantidade"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar estoque atual por ID: " + e.getMessage());
        }

        return estoqueAtual;
    }
    
    // Outros para alem do CRUD básico.
    
    public void movimentaSaldo(int associado_id, Produto produto, Armazem armazem, float quantidade){
        EstoqueAtual estoqueAtual = buscarPorId(associado_id, produto.getId(), armazem.getId());
        if(estoqueAtual != null){
            estoqueAtual.setQuantidade(estoqueAtual.getQuantidade() + quantidade);
            atualizar(estoqueAtual);
        } else {
            estoqueAtual = new EstoqueAtual(associado_id, produto, armazem, quantidade);
            inserir(estoqueAtual);
        }
    }
}
