package model.dto;

import java.time.LocalDate;

public class LoanDTO {
    private int id;
    private int bookId;
    private int readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private double penalty;

    public LoanDTO(int id, int bookId, int readerId, LocalDate borrowDate, LocalDate returnDate, double penalty) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.penalty = penalty;
    }

    public LoanDTO(int bookId, int readerId) {
        this(0, bookId, readerId, LocalDate.now(), null, 0.0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }
}
