package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

    // ============================================================
    // GERAR ID MANUAL (porque sua tabela não tem SERIAL)
    // ============================================================
    private Long gerarNovoId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(id),0) + 1 AS novo_id FROM item_pedido";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getLong("novo_id");
        }
        return 1L;
    }

    // ============================================================
    // SALVAR ITEM (caso precise salvar avulso — opcional)
    // ============================================================
    public Long salvar(ItemPedidoVO item) {

        String sql = "INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade_total, valor_unitario, valor_total) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            Long novoId = gerarNovoId(conn);

            stmt.setLong(1, novoId);
            stmt.setLong(2, item.getPedidoId());
            stmt.setLong(3, item.getProdutoId());
            stmt.setBigDecimal(4, item.getQuantidadeTotal());
            stmt.setBigDecimal(5, item.getValorUnitario());
            stmt.setBigDecimal(6, item.getValorTotal());

            stmt.executeUpdate();
            return novoId;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ============================================================
    // LISTAR TODOS OS ITENS DE UM PEDIDO
    // ============================================================
    public List<ItemPedidoVO> listarPorPedido(Long pedidoId) {

        List<ItemPedidoVO> lista = new ArrayList<>();

        String sql = "SELECT * FROM item_pedido WHERE pedido_id = ? ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pedidoId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ItemPedidoVO item = new ItemPedidoVO();

                item.setId(rs.getLong("id"));
                item.setPedidoId(pedidoId);
                item.setProdutoId(rs.getLong("produto_id"));
                item.setQuantidadeTotal(rs.getBigDecimal("quantidade_total"));
                item.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                item.setValorTotal(rs.getBigDecimal("valor_total"));

                lista.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
