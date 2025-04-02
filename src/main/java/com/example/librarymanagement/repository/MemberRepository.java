package com.example.librarymanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.librarymanagement.model.Member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MemberRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {

    Optional<Member> findByUserName(String userName);

    List<Member> findByMemberId(Integer memberId);
    
  // Count all registered members
  @Query("SELECT COUNT(m) FROM Member m")
  int getActiveMembers();

}
