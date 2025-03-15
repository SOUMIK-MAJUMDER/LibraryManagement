package com.example.librarymanagement.repository;


import java.util.*;

import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagement.model.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MemberRepository
// CRUD refers Create, Read, Update, Delete

public interface BookRepository extends CrudRepository<Book, Integer> {
     // Search for books containing the title (for searching)
     List<Book> findByTitleContainingIgnoreCase(String title);

     // Find a book by its exact title (for borrowing)
     Optional<Book> findByTitle(String title);

     List<Book> findByBorrowedBy_MemberId(Integer memberId);


}
