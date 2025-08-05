package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.dao.BookDao;
import br.edu.ifba.inf008.plugins.interfaces.IBookService;
import br.edu.ifba.inf008.plugins.model.Book;
import br.edu.ifba.inf008.utils.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.List;

public class BookService implements IBookService{

    private final BookDao bookDao;
    private final Validator validator;

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

        if (ValidationUtils.handleViolations(violations) == false) return false; 

        return bookDao.insertBook(title, author_name, isbn, published_year, copies_avaiable);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    public boolean updateBook(Book book) {

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (ValidationUtils.handleViolations(violations) == false) return false;

        return bookDao.updateBook(book);
    }

    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

}
