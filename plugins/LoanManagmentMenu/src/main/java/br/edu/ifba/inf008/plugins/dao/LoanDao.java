package br.edu.ifba.inf008.plugins.dao;

import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.utils.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class LoanDao {
    
    public List<Loan> searchAll(int userId) {

        List<Loan> loans = new ArrayList<>();
        String query = "SELECT loan_id , user_id, book_id, loan_date, return_date FROM loans WHERE user_id = ?";

        try {

            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userId + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Date sqlReturnDate = rs.getDate("return_date");
                LocalDate returnDate = (sqlReturnDate != null) ? sqlReturnDate.toLocalDate() : null;

                loans.add(new Loan(rs.getInt("loan_id"), 
                                   rs.getInt("book_id"), 
                                   rs.getInt("user_id"), 
                                   rs.getDate("loan_date").toLocalDate(), 
                                   returnDate));
            }

        } catch(Exception e) {
            System.err.println("Error listing loans: " + e.getMessage());
        }

        return loans;
    }

    public boolean InsertLoan(Loan loan) {

        String query = "INSERT INTO loans (book_id, user_id, loan_date, return_date) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, loan.getBookId());
            stmt.setInt(2, loan.getUserId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setNull(4, java.sql.Types.DATE);

            stmt.executeUpdate();

        } catch(Exception e) {
            System.err.println("Error registering the loan: " + e.getMessage());
            return false;
        }

        return true;
    }

    public Loan getLoanById(int id) {

        String query = "SELECT * FROM loans WHERE loan_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            Date sqlReturnDate = rs.getDate("return_date");
            LocalDate returnDate = (sqlReturnDate != null) ? sqlReturnDate.toLocalDate() : null;
            
            Loan loan = new Loan(rs.getInt("loan_id"), 
                                   rs.getInt("book_id"), 
                                   rs.getInt("user_id"), 
                                   rs.getDate("loan_date").toLocalDate(), 
                                   returnDate);

            return loan;

        } catch(Exception e) {
            System.err.println("Error searching the loan: " + e.getMessage());
            return null;
        }

    }

    public boolean updateLoan(Loan loan) {

        String query = "UPDATE loans SET return_date = ? WHERE loan_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
        
            stmt.setDate(1, Date.valueOf(loan.getReturnDate()));
            stmt.setInt(2, loan.getId());

            stmt.executeUpdate();

        } catch(Exception e) {
            System.err.println("Error updating the loan: " + e.getMessage());
            return false;
        }

        return true;
    }

}
