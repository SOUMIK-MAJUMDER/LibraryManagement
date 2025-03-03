package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.repository.MemberRepository;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping(path = "/memberform")
    public String inputform(Member member) {
        return "member_form";  // This will pre-populate the form fields if admin is provided.
    }

    @PostMapping(path = "/member/save")
    public String addNewUser(Member member) { 
        try{
            memberRepository.save(member);
            return "redirect:/member/success";  // Redirect to success page if the form is submitted successfully
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Redirect to error page if an exception occurs
        }
        
    }

    @GetMapping(path = "/member/success")
    public String showSuccessPage() {
        return "memberresult"; // Success page after form submission
    }

    @GetMapping(path = "/member/all")
    public @ResponseBody Iterable<Member> getAllUsers() {
        return memberRepository.findAll();
    }
}
