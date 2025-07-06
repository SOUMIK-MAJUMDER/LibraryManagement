package com.example.librarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.librarymanagement.model.Admin;
import com.example.librarymanagement.repository.AdminRepository;


import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.repository.BookRepository;;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BookRepository bookRepository;

	@Bean
	CommandLineRunner runner() 
	{
		return args -> 
		{
			Admin admin = new Admin("admin", "123", "John", "Doe", "1234567890");
			adminRepository.save(admin);

		};

	}

	@Bean
	CommandLineRunner runner1() {
    return args -> {
        Book book1 = new Book("Pather Dabi", "Sarat Chandra Chattopadhyay", "Novel", 1926, "9781981729319");
        Book book2 = new Book("Gitanjali", "Rabindranath Tagore", "Poetry", 1910, "9788171676752");
        Book book3 = new Book("Feluda Samagra – Volume 1", "Satyajit Ray", "Detective Fiction", 1965, "9788187934045");
        Book book4 = new Book("Aranyak", "Bibhutibhushan Bandyopadhyay", "Novel", 1939, "9788172153122");
        Book book5 = new Book("Devdas", "Sarat Chandra Chattopadhyay", "Tragic Romance", 1917, "9788129110184");


        bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);
		bookRepository.save(book5);
    };
}

}
