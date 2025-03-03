package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.repository.BookRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/bookform")
    public String inputForm(Book book) {
        return "bookdetails";  // Loads the book registration form
    }

    @PostMapping(path = "/book/save")
    public String addNewBook(Book book) {
        try {
            bookRepository.save(book);
            return "redirect:/book/success";  // Redirects to success page if book is added successfully
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Redirects to an error page if an exception occurs
        }
    }

    @GetMapping(path = "/book/success")
    public String showSuccessPage() {
        return "bookresult"; // Success page after book registration
    }

    @GetMapping(path = "/book/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
