package com.example.librarymanagement.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 150)
    private String authorName;

    @Column(length = 100)
    private String genre;

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(length = 13, unique = true)
    private String isbn;

    private boolean isBorrowed = false; // Default: Book is available

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowed_by_id", referencedColumnName = "memberId", foreignKey = @ForeignKey(name = "FK_BORROWED_BY"))
    private Member borrowedBy; // Ensure member exists before assigning

    private LocalDate borrowedDate; // Date when the book was borrowed
    private LocalDate returnDate; // Date when the book is to be returned

    private int extensionCount = 0;

    private int fineAmount = 0; // Fine in rupees

    // Default constructor
    public Book() {
    }

    // Parameterized constructor
    public Book(String title, String authorName, String genre, Integer publicationYear, String isbn) {
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

    public int getExtensionCount() {
        return extensionCount;
    }

    public void setExtensionCount(int extensionCount) {
        this.extensionCount = extensionCount;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void calculateFine() {
        if (returnDate != null && returnDate.isBefore(LocalDate.now())) {
            long overdueDays = ChronoUnit.DAYS.between(returnDate, LocalDate.now());
            this.fineAmount = (int) (overdueDays * 5); // ₹5 per day
        } else {
            this.fineAmount = 0;
        }
    }

    
    @PrePersist
    @PreUpdate
    public void updateBorrowedStatus() {
        this.isBorrowed = (this.borrowedBy != null
                && (this.returnDate == null || this.returnDate.isAfter(LocalDate.now())));
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", borrowedBy="
                + (borrowedBy != null ? borrowedBy.getFirstName() + " " + borrowedBy.getLastName() : "None") +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
