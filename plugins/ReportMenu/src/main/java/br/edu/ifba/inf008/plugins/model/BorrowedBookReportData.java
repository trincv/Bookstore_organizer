package br.edu.ifba.inf008.plugins.model;

import java.time.LocalDate;

public class BorrowedBookReportData {
    private String bookTitle;
    private String bookAuthor;
    private String userName;
    private LocalDate loanDate;

    public BorrowedBookReportData(String bookTitle, String bookAuthor, String userName, LocalDate loanDate) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.userName = userName;
        this.loanDate = loanDate;
    }

    
    public String getBookTitle() { return bookTitle; }
    public String getBookAuthor() { return bookAuthor; }
    public String getUserName() { return userName; }
    public LocalDate getLoanDate() { return loanDate; }

    
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
}