package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.TalhaoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalhaoDAO {

    /**
     * Busca todos os talhões associados a uma Área (Area_id).
     */
    public List<TalhaoVO> buscarPorAreaId(Long areaId) throws SQLException {
        String sql = """
            SELECT 
                id, 
                Area_id, 
                nome, 
                area_talhao, 
                observacoes, 
                status
            FROM talhao
            WHERE Area_id = ?
              AND status = 'Ativo'
            ORDER BY nome
        """;

        List<TalhaoVO> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, areaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TalhaoVO talhao = new TalhaoVO();
                    talhao.setId(rs.getLong("id"));
                    talhao.setAreaId(rs.getLong("Area_id"));
                    talhao.setNome(rs.getString("nome"));
                    talhao.setAreaTalhao(rs.getBigDecimal("area_talhao"));
                    talhao.setObservacoes(rs.getString("observacoes"));
                    talhao.setStatus(rs.getString("status"));
                    lista.add(talhao);
                }
            }
        }
        return lista;
    }
    
    // Implemente inserir, atualizar, remover conforme a sua necessidade...

    /**
     * Marca o talhão como Inativo no banco de dados.
     */
    public void inativarTalhao(Long talhaoId) throws SQLException {
        String sql = "UPDATE talhao SET status = 'Inativo' WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, talhaoId);
            ps.executeUpdate();
        }
    }
}