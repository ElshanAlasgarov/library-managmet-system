package az.edu.turing.dao.abstracts;

import az.edu.turing.dao.Dao;
import az.edu.turing.entity.Loan;

import java.util.List;

public abstract class LoanDao implements Dao<Loan, Integer> {

    public abstract List<Loan> getByReaderID(int readerId);
}
