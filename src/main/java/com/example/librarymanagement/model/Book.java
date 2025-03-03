package com.example.librarymanagement.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false, length = 255)
    private String title;

    private Integer authorId;

    @Column(length = 100)
    private String genre;

    private Integer publicationYear;

    @Column(length = 20)
    private String isbn;

    // Default constructor
    public Book() {
    }

    // Parameterized constructor
    public Book(Integer bookId, String title, Integer authorId, String genre, Integer publicationYear, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and Setters
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
