package com.example.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            // Connection은 JDBC 표준 인터페이스가 제공한다. JDBC 표준 인터페이스가 제공하는 Connection은 각 구현체별로 받아오는 Connection이 다르다.
            // 여기서는 H2 Database를 사용하고 있기 때문에, 내가 내려받은 라이브러리인 h2에서 Connection을 구현한 org.h2.jdbc.JdbcConnection을 가져온다.
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
