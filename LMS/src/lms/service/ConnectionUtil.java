package lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    
    Connection getConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
