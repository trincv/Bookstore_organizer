package br.edu.ifba.inf008.plugins.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class Loan {

    private int id;

    @NotNull(message = "The book's ID can't be null")
    private Integer bookId;

    @NotNull(message = "The user's ID can't be null")
    private Integer userId;

    @NotNull(message = "The loan's date can't be null")
    private LocalDate loanDate;

    private LocalDate returnDate = null; 

    public Loan(Integer userId, Integer bookId, LocalDate loanDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
    }
    
    public Loan(int id, Integer bookId, Integer userId, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isActive() {
        return this.returnDate == null;
    }
}


