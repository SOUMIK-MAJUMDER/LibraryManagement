// package com.example.librarymanagement.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class HomeController {
    
//     @GetMapping("/")  // Keeps existing homepage mapping
//     public String home(Model model) {
//         model.addAttribute("message", "Welcome to My Website!");
//         return "loginpage"; // Make sure loginpage.html exists inside templates/
//     }
//}




package com.example.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")  // Keeps existing homepage mapping
    public String home(Model model) {
        model.addAttribute("message", "Welcome to My Website!");
        return "loginpage"; // Make sure loginpage.html exists inside templates/
    }

    @GetMapping("/loginpage") // This ensures Go Back works
    public String showLoginPage() {
        return "loginpage"; // Returns loginpage.html
    }
}
