package controller;

import entity.Loan;
import exception.LoanNotFoundException;
import exception.ValidationException;
import model.dto.LoanDTO;
import service.LoanService;

import java.util.List;
import java.util.stream.Collectors;

public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    public LoanDTO borrowBook(int bookId, int readerId) {
        try {
            Loan loan = loanService.borrowBook(bookId, readerId);
            return new LoanDTO(loan.getId(), loan.getBookId(), loan.getReaderId(), loan.getBorrowDate(), loan.getReturnDate(), loan.getPenalty());
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public LoanDTO returnBook(int loanId) {
        try {
            Loan loan = loanService.returnBook(loanId);
            return new LoanDTO(loan.getId(), loan.getBookId(), loan.getReaderId(), loan.getBorrowDate(), loan.getReturnDate(), loan.getPenalty());
        } catch (LoanNotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<LoanDTO> getLoansByReaderId(int readerId) {
        List<Loan> loans = loanService.getLoansByReaderId(readerId);
        return loans.stream()
                .map(loan -> new LoanDTO(loan.getId(), loan.getBookId(), loan.getReaderId(), loan.getBorrowDate(), loan.getReturnDate(), loan.getPenalty()))
                .collect(Collectors.toList());
    }
}
