package main;

import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexão();

        // os comandos usados no SQL são chamados no mundo Java de statements
        // vai ser true quando o retorno for uma lista, false caso não haja retorno (insert, por exemplo)
        // boolean resultado = stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");

        PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        stm.execute();
        ResultSet rst = stm.getResultSet();

        while(rst.next()) {
            // pode passar o index (a partir de 1) ou label para pegar os elementos
            Integer id = rst.getInt("ID");
            System.out.println(id);
            String nome = rst.getString("NOME");
            System.out.println(nome);
            String desc = rst.getString("DESCRICAO");
            System.out.println(desc);
        }
        connection.close();
    }
}
