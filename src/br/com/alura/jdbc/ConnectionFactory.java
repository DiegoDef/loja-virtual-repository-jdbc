package br.com.alura.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public DataSource dataSource;

    // dessa forma tudo vai funcionar como o desenhor explicado anteriormente
    // agora não será aberto uma conexão direta com o banco quando tiver um requisição
    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        // vai carregar a pool já inicialmente com 15 conexões
        comboPooledDataSource.setMaxPoolSize(15);

        // a interface dataSource vai expor as informações do pool de conexões
        this.dataSource = comboPooledDataSource;
    }

    // isso fará a diferença quando for processar milhares de registros
    public Connection recuperarConexão() throws SQLException {
        return this.dataSource.getConnection();
    }
}
