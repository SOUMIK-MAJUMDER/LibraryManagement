package com.example.librarymanagement.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarymanagement.model.Admin;
import com.example.librarymanagement.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping(path = "/adminhome")
    public String showadminHomePage() {
        return "admin/admin_home"; // For 'admin_home.html' templates
    }

    @GetMapping(path = "/adminregistration")
    public String inputform(Admin admin) {
        return "admin/admin_registration_form"; // This will pre-populate the form fields if `admin` is provided.
    }

    @PostMapping(path = "/admin/save")
    public String addNewUser(Admin admin, Model model) {
        if (adminRepository.findByUserName(admin.getUserName()).isPresent()) {
            model.addAttribute("errorMessage", "Username already exists. Choose a different one.");
            return "admin/admin_registration_form";
        }
        try {
            adminRepository.save(admin);
            return "redirect:/admin/success"; // Redirect to success page
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to register admin. Please try again.");
            return "admin/admin_registration_form"; // Return to form with an error message
        }
    }
    

    @GetMapping(path = "/admin/success")
    public String showSuccessPage() {
        return "admin/adminresult"; // Success page after form submission
    }

    @GetMapping("/adminlogin")
    public String showLoginPage() {
        return "admin/admin_login";
    }

    @PostMapping("/adminlogin")
    public String loginadmin(@RequestParam String userName, @RequestParam String password, Model model,
            HttpSession session) {
        Optional<Admin> admin = adminRepository.findByUserName(userName);

        if (admin.isPresent()
                && admin.get().getUserName().equals(userName)
                && admin.get().getPassword().equals(password)) {
            session.setAttribute("loggedInAdmin", admin.get());
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin/admin_login";
        }
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");

        if (loggedInAdmin == null) {
            return "redirect:/admin_login";
        }

        model.addAttribute("admin", loggedInAdmin);
        return "admin/admin_dashboard";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/adminlogin";
    }

    @GetMapping(path = "/admin/all")
    public @ResponseBody Iterable<Admin> getAllUsers() {
        return adminRepository.findAll();
    }
}
