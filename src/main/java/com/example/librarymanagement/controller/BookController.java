package com.example.librarymanagement.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
            return "book/bookresult";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Redirect to error page if an exception occurs
        }
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


    @GetMapping("/mybooks")
    public String myBorrowedBooks(Model model, HttpSession session) {

        Integer memberId = (Integer) session.getAttribute("memberId"); // Get the logged-in member's ID from the session

        List<Book> borrowedBooks = bookRepository.findByBorrowedBy_MemberId(memberId); // Fetch books borrowed by the

        model.addAttribute("borrowedBooks", borrowedBooks);
        return "book/myborrowedbooks"; // Show the borrowed books page
    }


    

    // Borrow a book
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Integer bookId, @RequestParam Integer memberId, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.get();
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // Assign book to member
        Member member = optionalMember.get();
        book.setBorrowed(true);
        book.setBorrowedBy(member);
        book.setBorrowedDate(LocalDate.now());
        book.setReturnDate(LocalDate.now().plusDays(7)); // 7 day borrow period

        bookRepository.save(book);
        model.addAttribute("success", "Book borrowed successfully!");
        return "book/bookactionmessage";
    }




    // Return book
    // @PostMapping("/return")
    // public String returnBook(@RequestParam Integer bookId, RedirectAttributes redirectAttributes) {
    //     Optional<Book> optionalBook = bookRepository.findById(bookId);

    //     Book book = optionalBook.get();

    //     // Reset book status
    //     book.setBorrowed(false);
    //     book.setBorrowedBy(null);
    //     book.setBorrowedDate(null);
    //     book.setReturnDate(null);
    //     book.setExtensionCount(0);

    //     bookRepository.save(book);

    //     return "redirect:/book/mybooks";
    // }



    @PostMapping("/return")
    public String returnBook(@RequestParam Integer bookId, RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
    

            Book book = optionalBook.get();
            
            // Calculate fine before resetting book data
            book.calculateFine();
    
            // Store fine amount for display
            int fine = book.getFineAmount();

            if (fine > 0) 
            {
                redirectAttributes.addFlashAttribute("error", "Late return! Fine: ₹" + fine);
                return "book/payfine";
            } 
            // Reset book details
            book.setBorrowed(false);
            book.setBorrowedBy(null);
            book.setBorrowedDate(null);
            book.setReturnDate(null);
            book.setExtensionCount(0);
    
            bookRepository.save(book);
    
         
            redirectAttributes.addFlashAttribute("success", "Book returned successfully.");
            return "redirect:/book/mybooks";
    }
    


    @PostMapping("/finepaid")
    public String bookfinepaid(@RequestParam Integer bookId, RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
    

            Book book = optionalBook.get();

            book.setBorrowed(false);
            book.setBorrowedBy(null);
            book.setBorrowedDate(null);
            book.setReturnDate(null);
            book.setExtensionCount(0);
            book.setFineAmount(0); 
    
            bookRepository.save(book);
    
         
            redirectAttributes.addFlashAttribute("success", "Book returned successfully.");
            return "redirect:/book/mybooks";
    }

   


    @PostMapping("/extendReturnDate/{bookId}")
    public String extendReturnDate(@PathVariable Integer bookId, HttpSession session,RedirectAttributes redirectAttributes) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        Book book = optionalBook.get();

        // Extend return date by 7 days
        book.setReturnDate(book.getReturnDate().plusDays(7));
        book.setExtensionCount(book.getExtensionCount() + 1);
        bookRepository.save(book);

        redirectAttributes.addFlashAttribute("success", "Book return date extended successfully.");
        return "redirect:/book/mybooks"; // Redirect back to 'My Borrowed Books' after extension
    }






    // Delete book
    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Integer bookId, RedirectAttributes redirectAttributes) {

        bookRepository.deleteById(bookId);
        return "/book/bookremovemessage";
    }







    // Display books for members
    @GetMapping("/booklistadmin")
    public String listBooksForadmin(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/adminbooklist";
    }







    // Search books by title
    @GetMapping("/adminbooksearch")
    public String searchBookadmin(@RequestParam("query") String query, Model model) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(query);

        if (books.isEmpty()) {
            model.addAttribute("error", "No books found for: " + query);
        }
        model.addAttribute("books", books);
        model.addAttribute("query", query);
        return "book/adminbooksearchresults";
    }






    // Return all books as JSON
    @GetMapping("/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
