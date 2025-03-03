package com.example.librarymanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagement.model.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MemberRepository
// CRUD refers Create, Read, Update, Delete

public interface BookRepository extends CrudRepository<Book, Integer> {

}
