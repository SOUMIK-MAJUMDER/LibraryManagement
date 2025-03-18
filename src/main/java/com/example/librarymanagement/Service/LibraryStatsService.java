package com.example.librarymanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.dto.LibraryStatsDTO;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.MemberRepository;

@Service
public class LibraryStatsService {

    @Autowired private BookRepository bookRepository;
    @Autowired private MemberRepository memberRepository;

    public LibraryStatsDTO getLibraryStats() {
        int totalBooks = bookRepository.getTotalBooks();
        int activeMembers = memberRepository.getActiveMembers();
        int booksBorrowed = bookRepository.getBooksBorrowed();

        return new LibraryStatsDTO(totalBooks, activeMembers, booksBorrowed);
    }
}
