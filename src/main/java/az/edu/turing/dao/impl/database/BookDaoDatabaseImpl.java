package az.edu.turing.dao.impl.database;

import az.edu.turing.config.DatabaseConfig;
import az.edu.turing.dao.abstracts.BookDao;
import az.edu.turing.entity.Book;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoDatabaseImpl extends BookDao {

    private final  Connection connection;

    public BookDaoDatabaseImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Book add(Book book) {
        String sql = "INSERT INTO schema.books (title,author,category,status) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getCategory());
            preparedStatement.setBoolean(4, book.isStatus());

            preparedStatement.executeUpdate();
            System.out.println("Book successfully added to the database.");
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
        return book;
    }

    @Override
    public Optional<Book> getById(Integer id) {
        String sql = "SELECT id, title, author, category, status FROM schema.books WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("status")
                );
                return Optional.of(book);
            }

        } catch (SQLException | RuntimeException e) {
            System.err.println("Error finding book by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT id, title, author, category, status FROM schema.books";

        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException | RuntimeException e) {
            System.err.println("Error retrieving all books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE schema.books SET title = ?, author = ?, category = ?, status = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getCategory());
            preparedStatement.setBoolean(4,book.isStatus());
            preparedStatement.setInt(5,book.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book successfully updated.");
            } else {
                System.out.println("Book not found for update.");
            }
        } catch (SQLException | RuntimeException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM schema.books WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1,id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book successfully deleted.");
            } else {
                System.out.println("Book not found for deletion.");
            }
        } catch (SQLException | RuntimeException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}
