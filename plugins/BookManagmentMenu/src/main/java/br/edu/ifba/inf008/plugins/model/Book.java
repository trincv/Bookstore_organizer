package br.edu.ifba.inf008.plugins.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {

    @NotBlank(message = "Title can't be null")
    @Size(min = 3, max = 100, message = "The name must have between {min} and {max} characters")
    private String title;

    @NotBlank(message = "Author name can't be null")
    @Size(min = 3, max = 100, message = "The author must have between {min} and {max} characters")
    @Pattern(regexp = "^[\\p{L}\\s'-]+$", message = "The Author must contain just charracters, spaces and accents")
    private String author;

    @Pattern(regexp = "\\d+", message = "The ISBN must have only numbers")
    @Size(min = 13, max = 13, message = "The ISBN must contain 13 digits")
    private String isbn;

    @Min(value = 1450, message = "The published year must be after 1450")
    @Max(value = 2025, message = "the published year must be at past")
    private int publishedYear;

    @Min(value = 0, message = "The number of copies can't be negative")
    private int copiesAvailable;

    private int bookId;

    public Book(int bookId, String title, String author, String isbn, int publishedYear, int copiesAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.copiesAvailable = copiesAvailable;
    }

    public Book(String title, String author, String isbn, int publishedYear, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.copiesAvailable = copiesAvailable;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

}
