package br.edu.ifba.inf008.plugins.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.edu.ifba.inf008.plugins.model.Loan;

public interface ILoanService {

    public static LocalDate stringToLocalDate(String dateString){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error to convert string to date: " + e.getMessage());
            return null;
        }
    }

    public List<Loan> searchLoansByUserName(String userName);

    public Loan getLoanById(int id);

    public boolean registerDevolution(Loan loan, String dateString);

    public boolean registerLoan(int user, int book, Date loanDate);

}
