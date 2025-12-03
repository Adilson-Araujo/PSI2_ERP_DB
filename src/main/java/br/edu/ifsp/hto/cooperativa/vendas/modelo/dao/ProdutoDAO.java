package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

public class ProdutoDAO {

    public static class Produto {
        private Long id;
        private String nome;
        private BigDecimal preco; 

        public Produto(Long id, String nome, BigDecimal preco) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
        }

        public Long getId() { return id; }
        public String getNome() { return nome; }
        public BigDecimal getPreco() { return preco; }

        @Override
        public String toString() {
            return nome;
        }
    }

    // LISTAR TODOS (Para preencher o ComboBox na hora de criar pedido)
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();

        // CORREÇÃO: Buscamos da tabela PRODUTO, ligando com ESPECIE para pegar o PREÇO
        String sql = "SELECT p.id, p.nome, pp.valor " +
                     "FROM produto p " +
                     "INNER JOIN especie e ON p.especie_id = e.id " +
                     "INNER JOIN preco_ppa pp ON pp.especie_id = e.id " +
                     "ORDER BY p.nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                BigDecimal valor = rs.getBigDecimal("valor");

                if(valor == null) valor = BigDecimal.ZERO;

                lista.add(new Produto(id, nome, valor));
            }

        } catch (SQLException e) {
            System.out.println("ERRO listarTodos Produtos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
    
    // BUSCAR POR ID (Para exibir na tabela de detalhes)
    public Produto buscarPorId(Long id) {

        // CORREÇÃO: Buscamos na tabela PRODUTO pelo ID do produto (ex: 103)
        String sql = "SELECT p.id, p.nome, pp.valor " +
                     "FROM produto p " +
                     "INNER JOIN especie e ON p.especie_id = e.id " +
                     "INNER JOIN preco_ppa pp ON pp.especie_id = e.id " +
                     "WHERE p.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Long pid = rs.getLong("id");
                    String nome = rs.getString("nome");
                    BigDecimal valor = rs.getBigDecimal("valor");
                    
                    if(valor == null) valor = BigDecimal.ZERO;

                    return new Produto(pid, nome, valor);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO buscarPorId Produto: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}