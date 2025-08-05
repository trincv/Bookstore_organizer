package br.edu.ifba.inf008.plugins.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifba.inf008.plugins.model.Loan;

public interface ILoanService {

    public List<Loan> searchLoansByUserId(int userId);

    public Loan getLoanById(int id);

    public boolean registerDevolution(Loan loan, String dateString);

    public boolean registerLoan(int user, int book, LocalDate loanDate);

}
