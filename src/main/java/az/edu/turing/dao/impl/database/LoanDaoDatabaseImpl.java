package az.edu.turing.dao.impl.database;

import az.edu.turing.dao.abstracts.LoanDao;
import az.edu.turing.entity.Loan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanDaoDatabaseImpl extends LoanDao {

    private final Connection conn;

    public LoanDaoDatabaseImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Loan add(Loan loan) {
        String sql = """
                    INSERT INTO schema.loans (book_id, reader_id, borrow_date, return_date, penalty) 
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loan.getBookId());
            pstmt.setInt(2, loan.getReaderId());
            pstmt.setDate(3, Date.valueOf(loan.getBorrowDate()));
            pstmt.setDate(4, loan.getReturnDate() != null ? Date.valueOf(loan.getReturnDate()) : null);
            pstmt.setDouble(5, loan.getPenalty());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while saving loan: " + e.getMessage());
        }
        return loan;
    }

    @Override
    public Optional<Loan> getById(Integer id) {
        String sql = """
                    SELECT id, book_id, reader_id, borrow_date, return_date, penalty 
                    FROM schema.loans 
                    WHERE id = ?
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                var returnDate = rs.getDate("return_date");
                Loan loan = new Loan(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("reader_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        Optional.ofNullable(returnDate).map(Date::toLocalDate).orElse(null),
                        rs.getInt("penalty")
                );
                return Optional.of(loan);
            }

        } catch (SQLException e) {
            System.err.println("Error while finding loan by ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Loan> getAll() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT id, book_id, reader_id, borrow_date, return_date, penalty FROM schema.loans";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("reader_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate(),
                        rs.getInt("penalty")
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            System.err.println("Error while finding all loans: " + e.getMessage());
        }
        return loans;
    }

    @Override
    public void update(Loan loan) {
        String sql = """
                    UPDATE schema.loans 
                    SET book_id = ?, reader_id = ?, borrow_date = ?, return_date = ?, penalty = ? 
                    WHERE id = ?
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loan.getBookId());
            pstmt.setInt(2, loan.getReaderId());
            pstmt.setDate(3, Date.valueOf(loan.getBorrowDate()));
            pstmt.setDate(4, loan.getReturnDate() != null ? Date.valueOf(loan.getReturnDate()) : null);
            pstmt.setDouble(5, loan.getPenalty());
            pstmt.setInt(6, loan.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while updating loan: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM schema.loans WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error while deleting loan: " + e.getMessage());
        }
    }


    @Override
    public List<Loan> getByReaderID(int readerId) {
        String sql = "SELECT * FROM schema.loans WHERE reader_id = ?";
        List<Loan> loans = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, readerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan(
                            rs.getInt("id"),
                            rs.getInt("book_id"),
                            rs.getInt("reader_id"),
                            rs.getDate("borrow_date").toLocalDate(),
                            rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                            rs.getDouble("penalty")
                    );
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while fetching loans for reader ID: " + e.getMessage());
        }
        return loans;
    }
}
