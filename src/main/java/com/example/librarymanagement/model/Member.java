package com.example.librarymanagement.model;

import jakarta.persistence.*;


@Entity
//@Table(name = "members") // Optional, but good practice
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String userName;
    private String password;

    private String firstName;
    private String lastName;
    private String contactNo;
    private String address;
    private String subscriptionType;
    private int subscriptionAmount;

    // Default constructor (needed by JPA)
    public Member() {
    }

    // Parameterized constructor
    public Member(String userName,String password, Integer memberId, String firstName, String lastName, String contactNo, String address, String subscriptionType, int subscriptionAmount) {
        this.userName = userName;
        this.password = password;
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.address = address;
        this.subscriptionType = subscriptionType;
        this.subscriptionAmount = subscriptionAmount;
        
    }

    // Getters and Setters

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

}
