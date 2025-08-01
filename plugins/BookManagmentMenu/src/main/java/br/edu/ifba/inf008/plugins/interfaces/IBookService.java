package br.edu.ifba.inf008.plugins.interfaces;

import java.util.List;

import br.edu.ifba.inf008.plugins.model.Book;

public interface IBookService {
    
    public List<Book> searchBooksByTitle(String title);

    public boolean registerBook(String title, String author_name, String ISBN, int published_year, int copies_avaiable);

    public Book getBookById(int id);

    public boolean updateBook(Book book);

    public boolean deleteBook(int id);
}
