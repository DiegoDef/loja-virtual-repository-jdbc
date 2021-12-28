package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        // try-with-resources, ele garante que os recursos serão fechados no fim do try, deixa o código mais bonito
        // o try extende o AutoCloseable e por isso fecha os statements
        try (Connection connection = factory.recuperarConexão()) {

            // desabilita o commit automatico do código, com isso deverá ser uso o commit manualmente para mexer no banco
            // ou adiciona os dois produtos ou não adiciona ninguém
            connection.setAutoCommit(false);

            try (PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)
            ) {
                adicionarVariavel("SmarTV", "45 polegadas", stm);
                adicionarVariavel("Placa de vídeo", "RX 570", stm);

                // commit manual
                connection.commit();

                stm.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                // desfaz todas as mudanças
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

//        if(nome.equals("Placa de vídeo")) {
//              throw new RuntimeException("Não foi possível adicionar o produto");
//        }

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()){
            while(rst.next()) {
                int id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }
}
