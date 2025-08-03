package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.dao.LoanDao;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.interfaces.ILoanService;
import br.edu.ifba.inf008.plugins.model.Book;
import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.plugins.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LoanService implements ILoanService{

    private final LoanDao loanDao;
    private final Validator validator;
    private final UserDao userDao;

    public LoanService(LoanDao loanDao, UserDao userDao) {
        this.loanDao = loanDao;
        this.userDao = userDao;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * @param userName The name of the user to search for.
     * @return A list of loans for the first user found with that name, or an empty list if no user is found.
     */

    public List<Loan> searchLoansByUserName(String userName) {

        List<User> users = userDao.searchUsersByName(userName);

        if (users.isEmpty()) {
            System.out.println("No user found with the name: " + userName);
            return Collections.emptyList();
        }

        int userId = users.get(0).getId();
        
        return loanDao.searchAll(userId);
    }

    public Loan getLoanById(int id) {
        return loanDao.getLoanById(id);
    }

    public boolean registerDevolution(Loan loan, String returnDateString) {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        LocalDate returnDate;

        try {

            returnDate = LocalDate.parse(returnDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Please use dd/MM/yyyy.");
            return false;
        }
        
        if (returnDate.isBefore(loan.getLoanDate())) {
            System.err.println("Return date cannot be before the loan date.");
            return false;
        }

        loan.setReturnDate(returnDate);

        if(!loanDao.updateLoan(loan)) return false;
        
        Book book = bookService.getBookById(loan.getBookId());
        book.setCopiesAvailable((book.getCopiesAvailable() + 1));
        bookService.updateBook(book);

        return true;
    }

    public boolean registerLoan(int userId, int bookId, Date loanDate) {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();

        Loan newLoan = new Loan(userId, bookId, loanDate.toLocalDate());

        Set<ConstraintViolation<Loan>> violations = validator.validate(newLoan);

        if (!violations.isEmpty()) {
            System.err.println("Error in register loan:");
            for (ConstraintViolation<Loan> violation : violations) {
                System.err.println(" - " + violation.getMessage());
            }
            return false;
        }

        if(!loanDao.InsertLoan(newLoan)) return false;
        
        Book book = bookService.getBookById(newLoan.getBookId());
        book.setCopiesAvailable((book.getCopiesAvailable() - 1));
        bookService.updateBook(book);
        
        return true;
    }
}