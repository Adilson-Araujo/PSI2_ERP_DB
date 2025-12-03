package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;
import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;

public class PedidoDAO {

    public Long salvarPedido(PedidoVO pedido) {
        String sql = "INSERT INTO pedido (projeto_id, associado_id, data_criacao, status_pedido_id, valor_total) VALUES (?, ?, ?, ?, ?) RETURNING id";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (pedido.getProjetoId() == null || pedido.getProjetoId() == 0) {
                stmt.setNull(1, java.sql.Types.BIGINT);
            } else {
                stmt.setLong(1, pedido.getProjetoId());
            }
            stmt.setLong(2, pedido.getAssociadoId());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getDataCriacao()));
            stmt.setLong(4, pedido.getStatusPedidoId());
            stmt.setBigDecimal(5, pedido.getValorTotal());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void salvarItens(Long pedidoId, List<ItemPedidoVO> itens) {
        String sql = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade_total, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (ItemPedidoVO item : itens) {
                stmt.setLong(1, pedidoId);
                stmt.setLong(2, item.getProdutoId());
                stmt.setBigDecimal(3, item.getQuantidadeTotal());
                stmt.setBigDecimal(4, item.getValorUnitario());
                stmt.setBigDecimal(5, item.getValorTotal());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemPedidoVO> buscarItensPorPedido(Long pedidoId) {
        List<ItemPedidoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM item_pedido WHERE pedido_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedidoVO vo = new ItemPedidoVO();
                vo.setId(rs.getLong("id"));
                vo.setPedidoId(rs.getLong("pedido_id"));
                vo.setProdutoId(rs.getLong("produto_id"));
                vo.setQuantidadeTotal(rs.getBigDecimal("quantidade_total"));
                vo.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                lista.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS DE FILTRO QUE ESTÃO FALTANDO ---

    public List<PedidoVO> listarTodos() {
        return filtrarPedidos(null, null);
    }

    public List<PedidoVO> listarPorAssociado(Long associadoId) {
        return filtrarPedidos(null, associadoId);
    }

    public List<PedidoVO> filtrarPedidos(String termo, Long associadoId) {
        List<PedidoVO> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT p.*, proj.nome_projeto, a.nome_fantasia, s.descricao as status_desc ");
        sql.append("FROM pedido p ");
        sql.append("LEFT JOIN projeto proj ON p.projeto_id = proj.id ");
        sql.append("INNER JOIN associado a ON p.associado_id = a.id ");
        sql.append("INNER JOIN status_pedido s ON p.status_pedido_id = s.id ");
        sql.append("WHERE 1=1 ");

        if (associadoId != null) {
            sql.append("AND p.associado_id = ? ");
        }

        if (termo != null && !termo.trim().isEmpty()) {
            sql.append("AND (proj.nome_projeto ILIKE ? OR a.nome_fantasia ILIKE ? OR s.descricao ILIKE ?) ");
        }

        sql.append("ORDER BY p.data_criacao DESC");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (associadoId != null) {
                stmt.setLong(index++, associadoId);
            }

            if (termo != null && !termo.trim().isEmpty()) {
                String busca = "%" + termo + "%";
                stmt.setString(index++, busca);
                stmt.setString(index++, busca);
                stmt.setString(index++, busca);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoVO vo = new PedidoVO();
                vo.setId(rs.getLong("id"));
                vo.setAssociadoId(rs.getLong("associado_id"));
                vo.setProjetoId(rs.getLong("projeto_id"));
                if(rs.getTimestamp("data_criacao") != null)
                    vo.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                vo.setStatusPedidoId(rs.getLong("status_pedido_id"));
                vo.setStatusDescricao(rs.getString("status_desc")); 

                lista.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}