package service.impl;

import dao.abstracts.BookDao;
import dao.abstracts.LoanDao;
import dao.abstracts.ReaderDao;
import entity.Book;
import entity.Loan;
import exception.LoanNotFoundException;
import exception.ValidationException;
import service.LoanService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {

    private final BookDao bookDao;
    private final ReaderDao readerDao;
    private final LoanDao loanDao;

    public LoanServiceImpl(BookDao bookDao, ReaderDao readerDao, LoanDao loanDao) {
        this.bookDao = bookDao;
        this.readerDao = readerDao;
        this.loanDao = loanDao;
    }

    @Override
    public Loan borrowBook(int bookId, int readerId) {
        Optional<Book> optionalBook = bookDao.getById(bookId);
        if(optionalBook.isEmpty()){
            throw new ValidationException("Book with ID " + bookId + " not found.");
        }
        Book book = optionalBook.get();
        if(!book.isStatus()){
            throw new ValidationException("Book with ID " + book + " is already rented.");
        }
        if (readerDao.getById(readerId).isEmpty()) {
            throw new ValidationException("Reader with ID " + readerId + " not found.");
        }
        Loan loan = new Loan(0, bookId, readerId, LocalDate.now(), null, 0.0);
        Loan createdLoan = loanDao.add(loan);
        book.setStatus(false);
        bookDao.update(book);
        return createdLoan;
    }

    @Override
    public Loan returnBook(int loanId) {
        Optional<Loan> loanOptional = loanDao.getById(loanId);
        if (loanOptional.isEmpty()){
            throw new LoanNotFoundException("Loan with ID " + loanId + " not found.");
        }
        Loan loan = loanOptional.get();

        if (loan.getReturnDate() != null){
            throw new ValidationException("Loan with ID " + loanId + " is already returned.");
        }
        double penalty = calculatePenalty(loan);

        loan.setReturnDate(LocalDate.now());
        loan.setPenalty(penalty);
        loanDao.update(loan);

        Optional<Book> bookOptional = bookDao.getById(loan.getBookId());

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setStatus(true);
            bookDao.update(book);
        }
        return loan;
    }

    @Override
    public List<Loan> getLoansByReaderId(int readerId) {
        return loanDao.getByReaderID(readerId);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanDao.getAll();
    }

    @Override
    public double calculatePenalty(Loan loan) {
        LocalDate today = LocalDate.now();
        LocalDate dueDate = loan.getBorrowDate().plusDays(14);

        if (today.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, today);
            double penaltyPerDay = 0.5;
            return daysLate * penaltyPerDay;
        }
        return 0.0;
    }

}
