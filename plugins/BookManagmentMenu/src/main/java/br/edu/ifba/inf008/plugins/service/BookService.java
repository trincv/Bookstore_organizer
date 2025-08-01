package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.dao.BookDao;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.model.Book;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.scene.control.Alert;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService{

    private final BookDao bookDao;
    private final Validator validator;
    private List<String> violationList = new ArrayList<>(); 

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookDao.searchBooksByTitle(title);
    }

    public boolean registerBook(String title, String author_name, String isbn, int published_year, int copies_avaiable) {

        Book newBook = new Book(title, author_name, isbn, published_year, copies_avaiable);

        Set<ConstraintViolation<Book>> violations = validator.validate(newBook);

        if (!violations.isEmpty()) {
            System.err.println("Violations at the book registration:");
            for (ConstraintViolation<Book> violation : violations) {
                System.err.println(" - " + violation.getMessage());
            }
            return false;
        }

        return bookDao.insertBook(title, author_name, isbn, published_year, copies_avaiable);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    public boolean updateBook(Book book) {

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            System.err.println("Violations at the book registration:");
            for (ConstraintViolation<Book> violation : violations) {
                System.err.println(" - " + violation.getMessage());
            }
            return false;
        }

        return bookDao.updateBook(book);
    }

    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

}
