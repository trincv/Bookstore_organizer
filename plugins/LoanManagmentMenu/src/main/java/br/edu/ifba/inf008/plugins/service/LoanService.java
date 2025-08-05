package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.BookManagmentMenu;
import br.edu.ifba.inf008.plugins.UserManagmentMenu;
import br.edu.ifba.inf008.plugins.dao.LoanDao;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.interfaces.ILoanService;
import br.edu.ifba.inf008.plugins.interfaces.IUserService;
import br.edu.ifba.inf008.plugins.model.Book;
import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.plugins.model.User;
import br.edu.ifba.inf008.utils.UIUtils;
import br.edu.ifba.inf008.utils.ValidationUtils;
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
     * @param userId The id of the user to search for.
     * @return A list of loans for the user found with that id, or an empty list if no user is found.
     */

    public List<Loan> searchLoansByUserId(int userId) {

        User user = userDao.getUserById(userId);

        if (user == null) {
            UIUtils.showAlert("No user found with that id:" + userId);
            return Collections.emptyList();
        }
        
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

        } catch (Exception e) {
            UIUtils.showAlert(e.getMessage());
            return false;
        }
        
        if (returnDate.isBefore(loan.getLoanDate())) {
            UIUtils.showAlert("Return date cannot be before the loan date.");
            return false;
        }

        loan.setReturnDate(returnDate);

        if(!loanDao.updateLoan(loan)) return false;
        
        Book book = bookService.getBookById(loan.getBookId());
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookService.updateBook(book);
                
        return true;
    }

    public boolean registerLoan(int userId, int bookId, LocalDate loanDate) {

        IBookService bookService = BookManagmentMenu.getInstance().getBookService();
        IUserService userService = UserManagmentMenu.getInstance().getUserService();

        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (user == null) {
            UIUtils.showAlert("User not found");
            return false;
        }
        if (book == null) {
            UIUtils.showAlert("Book not found");
            return false;
        }

        Loan newLoan = new Loan(userId, bookId, loanDate);

        Set<ConstraintViolation<Loan>> violations = validator.validate(newLoan);

        if(ValidationUtils.handleViolations(violations) == false) return false;

        if(book.isAvailable() == false) {
            UIUtils.showAlert("The book is not available");
            return false;
        }

        if(!loanDao.InsertLoan(newLoan)) return false;
        
        book.setCopiesAvailable((book.getCopiesAvailable() - 1));
        bookService.updateBook(book);
        
        return true;
    }
}