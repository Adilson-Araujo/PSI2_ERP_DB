package br.edu.ifsp.hto.cooperativa.notafiscal.modelo.dao;

import br.edu.ifsp.hto.cooperativa.notafiscal.modelo.vo.NotaFiscalXmlVO;
import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalXmlDAO {

    public void adicionar(NotaFiscalXmlVO xml){
        String sql = """
                    INSERT INTO nota_fiscal_xml (id, hash, conteudo)
                    VALUES (?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setLong(1, xml.getId());
            p.setString(2, xml.getHash());
            p.setString(3, xml.getConteudo());

            p.executeUpdate();
        }catch(Exception ex){
            throw new RuntimeException("Erro ao inserir Nota Fiscal Xml", ex);
        }
    }

    public NotaFiscalXmlVO atualizar(NotaFiscalXmlVO xml) throws SQLException {
        String sql = """
            UPDATE nota_fiscal_xml
            SET hash = ?, conteudo = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, xml.getHash());
            ps.setString(2, xml.getConteudo());
            ps.setLong(3, xml.getId());
            ps.executeUpdate();
        }

        return buscarPorId(xml.getId());
    }

    public NotaFiscalXmlVO buscarPorId(Long id) {
        String sql = """
            SELECT id, hash, conteudo
            FROM nota_fiscal_xml
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NotaFiscalXmlVO xml = new NotaFiscalXmlVO();
                    xml.setId(rs.getLong("id"));
                    xml.setHash(rs.getString("hash"));
                    xml.setConteudo(rs.getString("conteudo"));
                    return xml;
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public List<NotaFiscalXmlVO> listarTodos() throws SQLException {
        String sql = """
            SELECT id, hash, conteudo
            FROM nota_fiscal_xml
            ORDER BY id
        """;

        List<NotaFiscalXmlVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NotaFiscalXmlVO xml = new NotaFiscalXmlVO();
                xml.setId(rs.getLong("id"));
                xml.setHash(rs.getString("hash"));
                xml.setConteudo(rs.getString("conteudo"));
                lista.add(xml);
            }
        }

        return lista;
    }
}
