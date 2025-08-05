package br.edu.ifba.inf008.plugins.dao;

import br.edu.ifba.inf008.plugins.model.Book;
import br.edu.ifba.inf008.utils.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
    
    public List<Book> searchBooksByTitle(String title) {

        List<Book> books = new ArrayList<>();
        String query = "SELECT book_id , title, author, isbn, published_year, copies_available FROM books WHERE title LIKE ?";

        try {

            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt("book_id"), 
                                   rs.getString("title"), 
                                   rs.getString("author"), 
                                   rs.getString("isbn"), 
                                   rs.getInt("published_year"), 
                                   rs.getInt("copies_available")));
            }

        } catch(Exception e) {
            System.err.println("Error listing books: " + e.getMessage());
        }

        return books;
    }

    public boolean insertBook(String title, String author, String isbn, int published_year, int copies_available) {

        String query = "INSERT INTO books (title, author, isbn, published_year, copies_available) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setInt(4, published_year);
            stmt.setInt(5, copies_available);

            stmt.executeUpdate();

        } catch(Exception e) {
            System.err.println("Error registering books: " + e.getMessage());
            return false;
        }

        return true;
    }

    public Book getBookById(int id) {

        String query = "SELECT * FROM books WHERE book_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            Book book = new Book(rs.getInt("book_id"), 
                                 rs.getString("title"), 
                                 rs.getString("author"), 
                                 rs.getString("isbn"), 
                                 rs.getInt("published_year"), 
                                 rs.getInt("copies_available"));

            return book;

        } catch(Exception e) {
            System.err.println("Error searching the book: " + e.getMessage());
            return null;
        }

    }

    public boolean updateBook(Book book) {

        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, published_year = ?, copies_available = ? WHERE book_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getPublishedYear());
            stmt.setInt(5, book.getCopiesAvailable());
            stmt.setInt(6, book.getBookId());

            stmt.executeUpdate();

        } catch(Exception e) {
            System.err.println("Error updating the book: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteBook(int id) {

        String query = "DELETE FROM books WHERE book_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
        
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch(Exception e) {
            System.err.println("Error deleting the book: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean titleExists(String title) {

        String query = "SELECT book_id , title, author, isbn, published_year, copies_available FROM books WHERE title LIKE ?";

        try {

            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title + "%");

            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) return true;

        } catch(Exception e) {
            System.err.println("Search title failed");
        }
        
        return false;
    }
}
