package com.example.librarymanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password; // No encryption, as per your request

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 15)
    private String contactNo;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 50)
    private String subscriptionType;

    @Column(nullable = false)
    private int subscriptionAmount;

    @OneToMany(mappedBy = "borrowedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> borrowedBooks; // List of books borrowed by the member

    // Default constructor (needed by JPA)
    public Member() {}

    // Parameterized constructor
    public Member(String userName, String password, String firstName, String lastName, 
                  String contactNo, String address, String subscriptionType, int subscriptionAmount) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.address = address;
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
    }

    // Getters and Setters

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public int getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(int subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Transient
    public boolean isActive() {
        return this.borrowedBooks != null && !this.borrowedBooks.isEmpty();
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", subscriptionAmount=" + subscriptionAmount +
                ", active=" + isActive() +
                '}';
    }
}
