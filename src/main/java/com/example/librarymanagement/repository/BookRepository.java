package com.example.librarymanagement.repository;


import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.librarymanagement.model.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MemberRepository
// CRUD refers Create, Read, Update, Delete


public interface BookRepository extends CrudRepository<Book, Integer> {
    
     List<Book> findByTitleContainingIgnoreCase(String title);  // Search for books containing the title (for searching)
     
     Optional<Book> findByTitle(String title); // Find a book by its exact title (for borrowing)

     List<Book> findByBorrowedBy_MemberId(Integer memberId);

     @Query("SELECT COUNT(b) FROM Book b WHERE b.isBorrowed = true")
     int getBooksBorrowed();  

     @Query("SELECT COUNT(b) FROM Book b")
     int getTotalBooks();


}
