package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalItemVO;

import java.sql.*;
import java.util.ArrayList;

public class NotaFiscalItemDAO {

    // ------------------------------------------------------------
    // Buscar por ID
    // ------------------------------------------------------------
    public NotaFiscalItemVO buscarId(Long id) {
        String sql = """
            SELECT id, nota_fiscal_eletronica_id, produto_id, cfop, ncm,
                   quantidade, valor_unitario, valor_total
            FROM nota_fiscal_item
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                NotaFiscalItemVO vo = new NotaFiscalItemVO();
                vo.setId(rs.getLong("id"));
                vo.setNotaFiscalEletronicaId(rs.getLong("nota_fiscal_eletronica_id"));
                vo.setProdutoId(rs.getInt("produto_id"));
                vo.setCfop(rs.getString("cfop"));
                vo.setNcm(rs.getString("ncm"));
                vo.setQuantidade(rs.getInt("quantidade"));
                vo.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                return vo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ------------------------------------------------------------
    // Adicionar
    // ------------------------------------------------------------
    public void adicionar(NotaFiscalItemVO vo) {
        String sql = """
            INSERT INTO nota_fiscal_item
                (nota_fiscal_eletronica_id, produto_id, cfop, ncm,
                 quantidade, valor_unitario, valor_total)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            p.setLong(1, vo.getNotaFiscalEletronicaId());
            p.setInt(2, vo.getProdutoId());
            p.setString(3, vo.getCfop());
            p.setString(4, vo.getNcm());
            p.setInt(5, vo.getQuantidade());
            p.setBigDecimal(6, vo.getValorUnitario());
            p.setBigDecimal(7, vo.getValorTotal());

            p.executeUpdate();

            ResultSet keys = p.getGeneratedKeys();
            if (keys.next()) {
                vo.setId(keys.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------
    // Atualizar
    // ------------------------------------------------------------
    public void atualizar(NotaFiscalItemVO vo) {
        String sql = """
            UPDATE nota_fiscal_item
            SET nota_fiscal_eletronica_id = ?, produto_id = ?, cfop = ?, ncm = ?,
                quantidade = ?, valor_unitario = ?, valor_total = ?
            WHERE id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, vo.getNotaFiscalEletronicaId());
            p.setInt(2, vo.getProdutoId());
            p.setString(3, vo.getCfop());
            p.setString(4, vo.getNcm());
            p.setInt(5, vo.getQuantidade());
            p.setBigDecimal(6, vo.getValorUnitario());
            p.setBigDecimal(7, vo.getValorTotal());
            p.setLong(8, vo.getId());

            p.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------
    // Obter todos
    // ------------------------------------------------------------
    public ArrayList<NotaFiscalItemVO> obterTodos() {
        ArrayList<NotaFiscalItemVO> lista = new ArrayList<>();

        String sql = """
            SELECT id, nota_fiscal_eletronica_id, produto_id, cfop, ncm,
                   quantidade, valor_unitario, valor_total
            FROM nota_fiscal_item
            ORDER BY id
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            while (rs.next()) {
                NotaFiscalItemVO vo = new NotaFiscalItemVO();
                vo.setId(rs.getLong("id"));
                vo.setNotaFiscalEletronicaId(rs.getLong("nota_fiscal_eletronica_id"));
                vo.setProdutoId(rs.getInt("produto_id"));
                vo.setCfop(rs.getString("cfop"));
                vo.setNcm(rs.getString("ncm"));
                vo.setQuantidade(rs.getInt("quantidade"));
                vo.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                vo.setValorTotal(rs.getBigDecimal("valor_total"));
                lista.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
