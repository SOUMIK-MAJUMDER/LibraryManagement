package com.example.librarymanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagement.model.Member;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MemberRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findByUserName(String userName);

}
