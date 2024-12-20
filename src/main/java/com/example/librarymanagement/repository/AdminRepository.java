package com.example.librarymanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagement.model.Admin;

// This will be AUTO IMPLEMENTED by Spring into a Bean called AdminRepository
// CRUD refers Create, Read, Update, Delete

public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
