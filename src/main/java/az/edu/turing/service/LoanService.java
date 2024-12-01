package service;

import entity.Loan;

import java.util.List;

public interface LoanService {

    Loan borrowBook(int bookId, int readerId);

    Loan returnBook(int loanId);

    List<Loan> getLoansByReaderId(int readerId);

    List<Loan> getAllLoans();

    double calculatePenalty(Loan loan);
}
