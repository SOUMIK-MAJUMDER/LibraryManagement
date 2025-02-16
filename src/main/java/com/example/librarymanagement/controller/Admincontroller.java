package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarymanagement.model.Admin;
import com.example.librarymanagement.repository.AdminRepository;

@Controller
public class AdminController {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping(path = "/adminform")
    public String inputform(Admin admin) {
        return "admin_form";  // This will pre-populate the form fields if `admin` is provided.
    }

    @PostMapping(path = "/admin/save")
    public String addNewUser(Admin admin) { 
        try{
            adminRepository.save(admin);
            return "redirect:/admin/success";  // Redirect to success page if the form is submitted successfully
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Redirect to error page if an exception occurs
        }
        
    }

    @GetMapping(path = "/admin/success")
    public String showSuccessPage() {
        return "result"; // Success page after form submission
    }

    @GetMapping(path = "/admin/all")
    public @ResponseBody Iterable<Admin> getAllUsers() {
        return adminRepository.findAll();
    }
}
