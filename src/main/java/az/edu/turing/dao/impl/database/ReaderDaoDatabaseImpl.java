package dao.impl.database;

import config.DatabaseConfig;
import dao.abstracts.ReaderDao;
import entity.Reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReaderDaoDatabaseImpl extends ReaderDao {

    private final Connection conn;

    public ReaderDaoDatabaseImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Reader add(Reader reader) {
        String sql = "INSERT INTO schema.readers (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reader.getName());
            pstmt.setString(2, reader.getEmail());
            pstmt.setString(3, reader.getPhone());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while saving reader: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Reader> getById(Integer id) {
        String sql = "SELECT id, name, email, phone FROM schema.readers WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Reader reader = new Reader(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                return Optional.of(reader);
            }

        } catch (SQLException e) {
            System.err.println("Error while finding reader by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Reader> getAll() {
        List<Reader> readers = new ArrayList<>();
        String sql = "SELECT id, name, email, phone FROM schema.readers";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reader reader = new Reader(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                readers.add(reader);
            }

        } catch (SQLException e) {
            System.err.println("Error while finding all readers: " + e.getMessage());
        }
        return readers;
    }

    @Override
    public void update(Reader reader) {
        String sql = "UPDATE schema.readers SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reader.getName());
            pstmt.setString(2, reader.getEmail());
            pstmt.setString(3, reader.getPhone());
            pstmt.setInt(4, reader.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while updating reader: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM schema.readers WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while deleting reader: " + e.getMessage());
        }
    }
}
