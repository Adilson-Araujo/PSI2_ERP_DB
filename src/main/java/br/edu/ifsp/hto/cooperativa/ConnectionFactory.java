package br.edu.ifsp.hto.cooperativa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Dados de conexão (EXEMPLO)
    private static final String URL = "jdbc:postgresql://localhost:5432/teste";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "root";

    // Método que retorna uma conexão.
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
