package dao.impl.database.schema;

import java.sql.Connection;
import java.sql.Statement;

public class TableInitializer {
    public static void initialize(Connection connection){

        try (Statement statement = connection.createStatement()){
            statement.execute("CREATE SCHEMA IF NOT EXISTS schema");

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS schema.books(
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(255) NOT NULL,
                    author VARCHAR(255) NOT NULL,
                    category VARCHAR(100) NOT NULL,
                    status BOOLEAN DEFAULT TRUE
                    )
            """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS schema.readers(
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                    phone VARCHAR(20)
                    )
            """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS schema.loans(
                    id SERIAL PRIMARY KEY,
                    book_id INT NOT NULL REFERENCES schema.books(id) ON DELETE CASCADE,
                    reader_id INT NOT NULL REFERENCES schema.reader(id) ON DELETE CASCADE,
                    borrow_date DATE NOT NULL,
                    return_date DATE
                    """);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
