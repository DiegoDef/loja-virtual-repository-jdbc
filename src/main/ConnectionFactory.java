package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection recuperarConexão() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC",
                "root", "root");
    }
}
