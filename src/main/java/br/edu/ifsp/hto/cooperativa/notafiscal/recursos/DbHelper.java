package br.edu.ifsp.hto.cooperativa.notafiscal.recursos;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper {

    public static long gerarPk(String nomeTabela){
        var sql = "select nextVal(?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTabela);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
