package az.edu.turing.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static final String POSTGRES_URL = System.getenv("POSTGRES_URL");
    public static final String POSTGRES_USER = System.getenv("POSTGRES_USER");
    public static final String POSTGRES_PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(POSTGRES_URL,POSTGRES_USER,POSTGRES_PASSWORD);

        } catch (SQLException e) {
            System.err.println("An error occurred while connecting to the database: " + e.getMessage());
        }

        return null;
    }
}
