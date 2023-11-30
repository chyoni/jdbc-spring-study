package com.example.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection1 = {}", connection1);
        log.info("connection2 = {}", connection2);
    }

    /**
     * DataSource를 구현한 구현체 DriverManagerDataSource를 사용해서 DB Connection을 얻어보자.
     * DriverManager는 JDBC를 사용할 때 직접 Connection을 한 개 얻어와서 작업하고 close 하기 위해 사용됐다.
     * */
    @Test
    void dataSourceDriverManager() throws SQLException {
        DriverManagerDataSource source = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(source);
    }

    /**
     * DataSource를 구현한 구현체 HikariCP를 사용해서 DB Connection Pool에서 Connection을 얻자.
     * */
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setPoolName("MyPool");

        useDataSource(hikariDataSource);
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection conn1 = dataSource.getConnection();
        Connection conn2 = dataSource.getConnection();

        log.info("connection1 = {}", conn1);
        log.info("connection2 = {}", conn2);
    }
}
