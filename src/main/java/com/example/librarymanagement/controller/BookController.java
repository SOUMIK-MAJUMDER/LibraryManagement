package com.example.librarymanagement.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BookController(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    // Book registration form
    @GetMapping("/add")
    public String showAddBookForm(Book book) {
        return "book/addbook"; // Displays the book registration form
    }

    // Save a new book
    @PostMapping("/save")
    public String saveBook(Book book) {
        try {
            bookRepository.save(book);
            return "redirect:/book/success"; // Redirect to success page
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Redirect to error page if an exception occurs
        }
    }

    // Success page after adding a book
    @GetMapping("/success")
    public String showSuccessPage() {
        return "book/bookresult";
    }

    // Display all books
    @GetMapping("/list")
    public String listAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/allbooksdetails";
    }

    // Display books for members
    @GetMapping("/memberbooklist")
    public String listBooksForMembers(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/booklistformembers";
    }

    // Search books by title
    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(query);

        if (books.isEmpty()) {
            model.addAttribute("error", "No books found for: " + query);
        }
        model.addAttribute("books", books);
        model.addAttribute("query", query);
        return "book/booksearchresults";
    }

    // Search books by title
    @GetMapping("/memberbooksearch")
    public String searchBooksmember(@RequestParam("query") String query, Model model) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(query);

        if (books.isEmpty()) {
            model.addAttribute("error", "No books found for: " + query);
        }
        model.addAttribute("books", books);
        model.addAttribute("query", query);
        return "book/memberbooksearchresults";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Integer bookId, @RequestParam Integer memberId, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            model.addAttribute("error", "Book not found.");
            return "book/booksearchresults";
        }

        Book book = optionalBook.get();
        if (book.isBorrowed()) {
            model.addAttribute("error", "Book is already borrowed.");
            return "book/booksearchresults";
        }

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            model.addAttribute("error", "Member not found.");
            return "book/booksearchresults";
        }

        // Assign book to member
        Member member = optionalMember.get();
        book.setBorrowed(true);
        book.setBorrowedBy(member);
        book.setBorrowedDate(LocalDate.now());
        book.setReturnDate(LocalDate.now().plusDays(14)); // 2 weeks borrow period

        bookRepository.save(book);
        model.addAttribute("success", "Book borrowed successfully!");
        return "book/bookactionmessage";
    }




    @PostMapping("/return")
    public String returnBook(@RequestParam Integer bookId, Model model) {
        System.out.println("Received request to return bookId: " + bookId);

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            System.out.println("Error: Book not found.");
            model.addAttribute("error", "Book not found.");
            return "book/booksearchresults";
        }

        Book book = optionalBook.get();
        if (!book.isBorrowed()) {
            System.out.println("Error: Book is not currently borrowed.");
            model.addAttribute("error", "This book is not borrowed.");
            return "book/bookactionmessage";
        }

        // Reset book status
        book.setBorrowed(false);
        book.setBorrowedBy(null);
        book.setBorrowedDate(null);
        book.setReturnDate(null);

        bookRepository.save(book);
        model.addAttribute("success", "Book returned successfully!");
        return "book/bookactionmessage";
    }


    @GetMapping("/mybooks")
public String myBorrowedBooks(Model model, HttpSession session) {
    // Get the logged-in member's ID from the session (guaranteed to exist)
    Integer memberId = (Integer) session.getAttribute("memberId");

    // Fetch books borrowed by the logged-in member
    List<Book> borrowedBooks = bookRepository.findByBorrowedBy_MemberId(memberId);

    model.addAttribute("borrowedBooks", borrowedBooks);
    return "book/myborrowedbooks"; // Show the borrowed books page
}


    // Return all books as JSON
    @GetMapping("/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
