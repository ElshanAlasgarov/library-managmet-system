package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    public static final String POSTGRES_URL = System.getenv("POSTGRES_URL");
    public static final String POSTGRES_USER = System.getenv("POSTGRES_USER");
    public static final String POSTGRES_PASSWORD = System.getenv("POSTGRES_PASSWORD");
    public static Connection getConnection() {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream("src/config/database.properties")) {
            properties.load(fis);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url,user,password);

        }catch (IOException e) {
            System.err.println("An error occurred while reading the configuration file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("An error occurred while connecting to the database: " + e.getMessage());
        }

        return null;
    }
}
