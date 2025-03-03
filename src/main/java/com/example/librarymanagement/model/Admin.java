// package com.example.librarymanagement.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "admin") // Optional, but good practice
// public class Admin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer adminId;

//     private String firstName;
//     private String lastName;
//     private String contactNo;
//     private String userName;
//     private String password;

//     // Default constructor (required by JPA)
//     public Admin() {
//     }

//     // Parameterized constructor
//     public Admin( String firstName, String lastName, String contactNo, String userName, String password) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.contactNo = contactNo;
//         this.userName = userName;
//         this.password = password;
//     }

//     // Getters and Setters
//     public Integer getAdminId() {
//         return adminId;
//     }

//     public void setAdminId(Integer adminId) {
//         this.adminId = adminId;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public String getContactNo() {
//         return contactNo;
//     }

//     public void setContactNo(String contactNo) {
//         this.contactNo = contactNo;
//     }

//     public String getUserName() {
//         return userName;
//     }

//     public void setUserName(String userName) {
//         this.userName = userName;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }
// }







package com.example.librarymanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin") // Optional, but good practice
public class Admin {

    @Id
    private String userName;
    
    private String password;

    // Default constructor (required by JPA)
    public Admin() {
    }

    // Parameterized constructor
    public Admin( String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
