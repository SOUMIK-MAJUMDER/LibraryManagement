package com.example.librarymanagement.dto;

public class LibraryStatsDTO {
    private int totalBooks;
    private int activeMembers;
    private int booksBorrowed;

    // Constructor
    public LibraryStatsDTO(int totalBooks, int activeMembers, int booksBorrowed) {
        this.totalBooks = totalBooks;
        this.activeMembers = activeMembers;
        this.booksBorrowed = booksBorrowed;
    }

    // Getters
    public int getTotalBooks() { return totalBooks; }
    public int getActiveMembers() { return activeMembers; }
    public int getBooksBorrowed() { return booksBorrowed; }

    // Setters (Optional)
    public void setTotalBooks(int totalBooks) { this.totalBooks = totalBooks; }
    public void setActiveMembers(int activeMembers) { this.activeMembers = activeMembers; }
    public void setBooksBorrowed(int booksBorrowed) { this.booksBorrowed = booksBorrowed; }
}
