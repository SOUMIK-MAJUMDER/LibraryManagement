package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "/")
    public String inputform(Admin admin) {
        return "admin_form";
    }

    @GetMapping(path = "/result")
    public String result() {
        return "result";  // result.html (your congratulations page)
    }

    @PostMapping(path = "/") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam int AdminId, @RequestParam String FirstName, @RequestParam String LastName) {
        Admin n = new Admin();
        n.setAdminId(AdminId);
        n.setFirstName(FirstName);
        n.setLastName(LastName);
        adminRepository.save(n);
        return "redirect:/result";
    }

   


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Admin> getAllUsers() {
        return adminRepository.findAll();
    }
}

