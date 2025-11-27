package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Armazem;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Movimentacao;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Tipo;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Origem;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovimentacaoDAO {
    private static MovimentacaoDAO instancia = null;
    private static final TipoDAO DAO_tipo = TipoDAO.getInstance();
    private static final OrigemDAO DAO_origem = OrigemDAO.getInstance();
    private static final ProdutoDAO DAO_produto = ProdutoDAO.getInstance();
    private static final ArmazemDAO DAO_armazem = ArmazemDAO.getInstance();
    private static final EstoqueAtualDAO DAO_estoque_atual = EstoqueAtualDAO.getInstance();
    private static final Map<Integer, Movimentacao> cache = new HashMap<>();
    
    private MovimentacaoDAO(){}
    public static MovimentacaoDAO getInstance(){
        if (instancia == null) instancia = new MovimentacaoDAO();
        return instancia;
    }
    
    private void validarMovimentacao(Movimentacao movimentacao) throws Exception {
        if (movimentacao == null) throw new Exception("Movimentação inválida.");
        if (movimentacao.getTipo() == null || movimentacao.getTipo().getId() <= 0) throw new Exception("Tipo inválido.");
        if (movimentacao.getOrigem() == null || movimentacao.getOrigem().getId() <= 0) throw new Exception("Origem inválida.");
        if (movimentacao.getProduto() == null || movimentacao.getProduto().getId() <= 0) throw new Exception("Produto inválido.");
        if (movimentacao.getArmazem() == null || movimentacao.getArmazem().getId() <= 0) throw new Exception("Armazem inválido.");
        if (movimentacao.getAssociadoId() <= 0) throw new Exception("Associado inválido.");
        if (movimentacao.getQuantidade() <= 0) throw new Exception("Quantidade deve ser maior que zero.");
    }
    
    public boolean inserir(Movimentacao movimentacao) throws Exception {
        validarMovimentacao(movimentacao);
        if(movimentacao.getId() != -1) throw new Exception("Registro já inserido. Tente atualiza-lo");
        
        String sql = "INSERT INTO movimentacao (tipo_id, origem_id, produto_id, armazem_id, associado_id, quantidade, data_movimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movimentacao.getTipo().getId());
            stmt.setInt(2, movimentacao.getOrigem().getId());
            stmt.setInt(3, movimentacao.getProduto().getId());
            stmt.setInt(4, movimentacao.getArmazem().getId());
            stmt.setInt(5, movimentacao.getAssociadoId());
            stmt.setFloat(6, movimentacao.getQuantidade());
            stmt.setTimestamp(7, movimentacao.getData());
            stmt.executeUpdate();
            
            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    movimentacao.setId(idGerado); // atualiza o objeto com o novo ID
                    cache.put(idGerado, movimentacao);
                }
            }
            
            // Informa DAO_estoque_atual sobre a modificação.
            DAO_estoque_atual.movimentaSaldo(
                    movimentacao.getAssociadoId(), 
                    movimentacao.getProduto(),
                    movimentacao.getArmazem(),
                    movimentacao.getQuantidade());
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir movimentação: " + e.getMessage());
            return false;
        }
    }

    public Movimentacao buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, tipo_id, origem_id, produto_id, armazem_id, associado_id, quantidade, data_movimento FROM movimentacao WHERE id = ?";
        Movimentacao movimentacao = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tipo tipo = DAO_tipo.buscarPorId(rs.getInt("tipo_id"));
                Origem origem = DAO_origem.buscarPorId(rs.getInt("origem_id"));
                Produto produto = DAO_produto.buscarPorId(rs.getInt("produto_id"));
                Armazem armazem = DAO_armazem.buscarPorId(rs.getInt("armazem_id"));
                
                movimentacao = new Movimentacao(
                        rs.getInt("id"),
                        tipo,
                        origem,
                        produto,
                        armazem,
                        rs.getInt("associado_id"),
                        rs.getFloat("quantidade"),
                        rs.getTimestamp("data_movimento"));
                cache.put(id, movimentacao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar movimentacao por ID: " + e.getMessage());
        }

        return movimentacao;
    }

    public boolean atualizar(Movimentacao movimentacao) throws Exception {
        validarMovimentacao(movimentacao);
        
        String sql = "UPDATE movimentacao SET tipo_id = ?, origem_id = ?, produto_id = ?, armazem_id = ?, associado_id = ?, quantidade = ?, data_movimento = ?  WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movimentacao.getTipo().getId());
            stmt.setInt(2, movimentacao.getOrigem().getId());
            stmt.setInt(3, movimentacao.getProduto().getId());
            stmt.setInt(4, movimentacao.getArmazem().getId());
            stmt.setInt(5, movimentacao.getAssociadoId());
            stmt.setFloat(6, movimentacao.getQuantidade());
            stmt.setTimestamp(7, movimentacao.getData());
            stmt.setInt(8, movimentacao.getId());

            int linhasAfetadas = stmt.executeUpdate();
            
            // Informa DAO_estoque_atual sobre a modificação.
            
            DAO_estoque_atual.movimentaSaldo(
                    movimentacao.getAssociadoId(), 
                    movimentacao.getProduto(),
                    movimentacao.getArmazem(),
                    movimentacao.getMudanca());
            
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar movimentacao: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM movimentacao WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Informa DAO_estoque_atual sobre a modificação.
            Movimentacao movimentacao = buscarPorId(id);
            DAO_estoque_atual.movimentaSaldo(
                    movimentacao.getAssociadoId(), 
                    movimentacao.getProduto(),
                    movimentacao.getArmazem(),
                    movimentacao.getQuantidade());
            
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            
            cache.remove(id);
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir movimentacao: " + e.getMessage());
            return false;
        }
    }

    public List<Movimentacao> listarTodas() {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String sql = "SELECT id, tipo_id, origem_id, produto_id, armazem_id, associado_id, quantidade, data_movimento FROM movimentacao";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                Tipo tipo = DAO_tipo.buscarPorId(rs.getInt("tipo_id"));
                Origem origem = DAO_origem.buscarPorId(rs.getInt("origem_id"));
                Produto produto = DAO_produto.buscarPorId(rs.getInt("produto_id"));
                Armazem armazem = DAO_armazem.buscarPorId(rs.getInt("armazem_id"));
                
                if (!cache.containsKey(id)) {
                    Movimentacao movimentacao = new Movimentacao(
                            id,
                            tipo,
                            origem,
                            produto,
                            armazem,
                            rs.getInt("associado_id"),
                            rs.getFloat("quantidade"),
                            rs.getTimestamp("data_movimento"));
                    cache.put(id, movimentacao);
                }
                movimentacoes.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações: " + e.getMessage());
        }

        return movimentacoes;
    }
    
    // Outros além do CRUD básico.
    
    public List<Movimentacao> listarPorProduto(int associado_id, int produto_id) {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String sql = "SELECT id, tipo_id, origem_id, produto_id, armazem_id, associado_id, quantidade, data_movimento FROM movimentacao WHERE associado_id = ? AND produto_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, associado_id);
            stmt.setInt(2, produto_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                
                Tipo tipo = DAO_tipo.buscarPorId(rs.getInt("tipo_id"));
                Origem origem = DAO_origem.buscarPorId(rs.getInt("origem_id"));
                Produto produto = DAO_produto.buscarPorId(rs.getInt("produto_id"));
                Armazem armazem = DAO_armazem.buscarPorId(rs.getInt("armazem_id"));
                
                if (!cache.containsKey(id)) {
                    Movimentacao movimentacao = new Movimentacao(
                            id,
                            tipo,
                            origem,
                            produto,
                            armazem,
                            rs.getInt("associado_id"),
                            rs.getFloat("quantidade"),
                            rs.getTimestamp("data_movimento"));
                    cache.put(id, movimentacao);
                }
                movimentacoes.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações: " + e.getMessage());
        }

        return movimentacoes;
    }
}
