package org.example.repository;

import org.example.exceptions.SQLUpdateException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static final String username = "postgres";
    private static final String password = "postgrespw";
    private static final String url = "jdbc:postgresql://localhost:32768/postgres";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            throw new SQLUpdateException(e.getMessage(), connection);
        }
        return connection;
    }
}
