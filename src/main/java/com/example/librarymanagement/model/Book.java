package com.example.librarymanagement.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false, length = 255)
    private String title;

    private String authorName;

    @Column(length = 100)
    private String genre;

    private Integer publicationYear;

    @Column(length = 13)
    private String isbn;

    private boolean isBorrowed = false; // Track book availability

    @ManyToOne
    @JoinColumn(name = "borrowed_by_id")
    private Member borrowedBy; // The member who borrowed the book

    private LocalDate borrowedDate; // Date when the book was borrowed
    private LocalDate returnDate; // Date when the book is to be returned

    // Default constructor
    public Book() {
    }

    // Parameterized constructor
    public Book(Integer bookId, String title, String authorName, String genre, Integer publicationYear, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.authorName = authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Member getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Member borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() { 
        return returnDate;
    }
    

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
}
