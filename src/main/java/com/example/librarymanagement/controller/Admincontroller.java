package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Admin;
import com.example.librarymanagement.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class Admincontroller {

    private final AdminRepository adminRepository;

    // Constructor-based Dependency Injection for AdminRepository
    public Admincontroller(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // GET: Fetch all admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // GET: Fetch an admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST: Add a new admin
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    // PUT: Update an admin
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        return adminRepository.findById(id)
                .map(existingAdmin -> {
                    existingAdmin.setFirstName(updatedAdmin.getFirstName());
                    existingAdmin.setContactNo(updatedAdmin.getContactNo());
                    Admin savedAdmin = adminRepository.save(existingAdmin);
                    return ResponseEntity.ok(savedAdmin);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE: Delete an admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
