package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexão();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stm.setInt(1, 2);
        stm.execute();

        // retorna quantas linhas foram modificadas
        Integer linhasModificadas = stm.getUpdateCount();

        System.out.println("Quantidades de linhas que foram modficadas: " + linhasModificadas);
    }
}
