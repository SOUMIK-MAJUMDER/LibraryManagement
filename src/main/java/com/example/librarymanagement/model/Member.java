package com.example.librarymanagement.model;

import jakarta.persistence.*;


@Entity
//@Table(name = "members") // Optional, but good practice
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String firstName;
    private String lastName;
    private String contactNo;
    private String address;
    private String subscriptionType;
    private int subscriptionAmount;
    private double fineAmount;

    // Default constructor (needed by JPA)
    public Member() {
    }

    // Parameterized constructor
    public Member(Integer memberId, String firstName, String lastName, String contactNo, String address, String subscriptionType, int subscriptionAmount, double fineAmount) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.address = address;
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
        this.fineAmount = fineAmount;
    }

    // Getters and Setters
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
