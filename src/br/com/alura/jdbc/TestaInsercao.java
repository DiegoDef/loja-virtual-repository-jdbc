package br.com.alura.jdbc;

import br.com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexão();

        // usando o PrepareStatement dessa forma evitamos problemas com SQLInjection
        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, "Mouse");
        stm.setString(2, "Mouse sem fio");

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
        connection.close();
    }
}
