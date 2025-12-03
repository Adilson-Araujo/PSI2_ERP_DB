package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.AssociadoItemPedidoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociadoItemPedidoDAO {

    // ==========================
    // LIMPA TODAS AS ATRIBUIÇÕES
    // ==========================
    public void deletarAtribuicoesPorItem(Long itemPedidoId) {
        String sql = "DELETE FROM associado_item_pedido WHERE item_pedido_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, itemPedidoId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==========================
    // SALVA UMA ATRIBUIÇÃO
    // ==========================
    public String salvarAtribuicao(AssociadoItemPedidoVO vo) {

        String sql = "INSERT INTO associado_item_pedido " +
                "(associado_id, item_pedido_id, quantidade_atribuida) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vo.getAssociadoId());
            stmt.setLong(2, vo.getItemPedidoId());
            stmt.setBigDecimal(3, vo.getQuantidadeAtribuida());

            stmt.executeUpdate();
            return "sucesso";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar atribuição: " + e.getMessage();
        }
    }

    // ==========================
    // BUSCA ATRIBUIÇÕES DE UM ITEM
    // ==========================
    public List<AssociadoItemPedidoVO> listarPorItem(Long itemId) {

        List<AssociadoItemPedidoVO> lista = new ArrayList<>();

        String sql = "SELECT * FROM associado_item_pedido WHERE item_pedido_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, itemId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AssociadoItemPedidoVO vo = new AssociadoItemPedidoVO();

                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setItemPedidoId(rs.getLong("item_pedido_id"));
                vo.setQuantidadeAtribuida(rs.getBigDecimal("quantidade_atribuida"));

                lista.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
