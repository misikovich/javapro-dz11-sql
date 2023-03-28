package org.example.exceptions;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLUpdateException extends RuntimeException {
    public SQLUpdateException(String message, Connection connection) {
        super(message);
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SQLUpdateException(e.getMessage());
            }
        }
    }

    public SQLUpdateException(String message) {
        super(message);
    }
}
