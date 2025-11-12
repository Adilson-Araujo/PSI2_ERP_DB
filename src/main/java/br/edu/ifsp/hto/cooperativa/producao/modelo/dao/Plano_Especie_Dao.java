package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.PlanoEspecieVO;
import java.sql.*;
import java.util.ArrayList;

public class Plano_Especie_Dao {

    private static final String URL = "jdbc:postgresql://localhost:5432/cooperativa";
    private static final String USER = "usuario";
    private static final String PASSWORD = "senha";

    public PlanoEspecieVO buscarId(int id, int especieId) {
        PlanoEspecieVO vo = null;
        String sql = "SELECT * FROM plano_especie WHERE id = ? AND Especie_id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement p = conexao.prepareStatement(sql)) {

            p.setInt(1, id);
            p.setInt(2, especieId);

            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    vo = new PlanoEspecieVO();
                    vo.setId(rs.getInt("id"));
                    vo.setEspecieId(rs.getInt("Especie_id"));
                    vo.setNomePlano(rs.getString("nome_plano"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public String adicionar(PlanoEspecieVO vo) {
        String sql = "INSERT INTO plano_especie (Especie_id, nome_plano) VALUES (?, ?)";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement p = conexao.prepareStatement(sql)) {

            p.setInt(1, vo.getEspecieId());
            p.setString(2, vo.getNomePlano());

            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "ERROR";

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String remover(int id, int especieId) {
        String sql = "DELETE FROM plano_especie WHERE id = ? AND Especie_id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement p = conexao.prepareStatement(sql)) {

            p.setInt(1, id);
            p.setInt(2, especieId);

            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "NAO_ENCONTRADO";

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String atualizar(PlanoEspecieVO vo) {
        String sql = "UPDATE plano_especie SET nome_plano = ? WHERE id = ? AND Especie_id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement p = conexao.prepareStatement(sql)) {

            p.setString(1, vo.getNomePlano());
            p.setInt(2, vo.getId());
            p.setInt(3, vo.getEspecieId());

            int changed = p.executeUpdate();
            return changed > 0 ? "OK" : "NAO_ATUALIZADO";

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ArrayList<PlanoEspecieVO> obterTodos() {
        ArrayList<PlanoEspecieVO> list = new ArrayList<>();
        String sql = "SELECT * FROM plano_especie";

        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement p = conexao.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            while (rs.next()) {
                PlanoEspecieVO vo = new PlanoEspecieVO();
                vo.setId(rs.getInt("id"));
                vo.setEspecieId(rs.getInt("Especie_id"));
                vo.setNomePlano(rs.getString("nome_plano"));
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
