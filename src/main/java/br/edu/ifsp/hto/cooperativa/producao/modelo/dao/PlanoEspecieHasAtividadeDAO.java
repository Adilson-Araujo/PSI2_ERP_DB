package br.edu.ifsp.hto.cooperativa.producao.modelo.dao;

import br.edu.ifsp.hto.cooperativa.producao.modelo.vo.PlanoEspecieHasAtividadeVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoEspecieHasAtividadeDAO {

    private Connection conexao;

    public PlanoEspecieHasAtividadeDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Inserir um novo vínculo
    public void inserir(PlanoEspecieHasAtividadeVO vo) throws SQLException {
        String sql = "INSERT INTO Plano_Especie_has_Atividade (Plano_especie_id, Atividade_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, vo.getPlanoEspecieId());
            stmt.setLong(2, vo.getAtividadeId());
            stmt.executeUpdate();
        }
    }

    // Listar todos os vínculos
    public List<PlanoEspecieHasAtividadeVO> listarTodos() throws SQLException {
        List<PlanoEspecieHasAtividadeVO> lista = new ArrayList<>();
        String sql = "SELECT Plano_especie_id, Atividade_id FROM Plano_Especie_has_Atividade";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PlanoEspecieHasAtividadeVO vo = new PlanoEspecieHasAtividadeVO(
                        rs.getLong("Plano_especie_id"),
                        rs.getLong("Atividade_id")
                );
                lista.add(vo);
            }
        }
        return lista;
    }

    // Deletar um vínculo específico
    public void deletar(long planoEspecieId, long atividadeId) throws SQLException {
        String sql = "DELETE FROM Plano_Especie_has_Atividade WHERE Plano_especie_id = ? AND Atividade_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, planoEspecieId);
            stmt.setLong(2, atividadeId);
            stmt.executeUpdate();
        }
    }
}
