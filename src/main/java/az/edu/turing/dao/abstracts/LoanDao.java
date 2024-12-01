package dao.abstracts;

import dao.Dao;
import entity.Loan;

import java.util.List;

public abstract class LoanDao implements Dao<Loan, Integer> {

    public abstract List<Loan> getByReaderID(int readerId);
}
