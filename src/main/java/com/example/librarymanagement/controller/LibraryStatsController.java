package com.example.librarymanagement.controller; // Correct package

import com.example.librarymanagement.dto.LibraryStatsDTO;
import com.example.librarymanagement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class LibraryStatsController {

    @Autowired
    private LibraryStatsService libraryStatsService;

    @GetMapping
    public LibraryStatsDTO getLibraryStats() {
        return libraryStatsService.getLibraryStats();
    }
}
