package main;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        String nome = "Mouse";
        String descricao = "Mouse sem fio";

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexão();

        PreparedStatement stm = connection.prepareStatement(
                "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            int id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
        connection.close();
    }
}
