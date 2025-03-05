package com.example.librarymanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin") // Optional, but good practice
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    private String userName;
    
    private String password;

    private String firstName;
    private String lastName;
    private String contactNo;

    // Default constructor (required by JPA)
    public Admin() {
    }

    // Parameterized constructor
    public Admin(String userName, String password, String firstName, String lastName, String contactNo) 
    {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
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
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

}







// package com.example.librarymanagement.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "admin") // Optional, but good practice
// public class Admin {

//     @Id
//     private String userName;
    
//     private String password;

//     // Default constructor (required by JPA)
//     public Admin() {
//     }

//     // Parameterized constructor
//     public Admin( String userName, String password) {
//         this.userName = userName;
//         this.password = password;
//     }

//     // Getters and Setters

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
