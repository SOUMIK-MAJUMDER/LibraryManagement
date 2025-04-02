package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping(path = "/memberhome")
    public String showMemberHomePage() {
        return "member/member_home"; // For 'member_dashboard.html' templates
    }

    @GetMapping(path = "/memberregistration")
    public String inputform(Member member) {
        return "member/member_registration_form"; // This will pre-populate the form fields if admin is provided.
    }

    @PostMapping(path = "/member/save")
    public String addNewUser(Member member) {
        try {
            memberRepository.save(member);
            return "redirect:/member/success"; // Redirect to success page if the form is submitted successfully
        } catch (Exception e) {
            e.printStackTrace();
            return "errormessage"; // Redirect to error page if an exception occurs
        }
    }

    @GetMapping(path = "/member/success")
    public String showSuccessPage() {
        return "member/memberresult"; // Success page after form submission
    }

    @GetMapping("/memberlogin")
    public String showLoginPage() {
        return "member/member_login";
    }

    @PostMapping("/memberlogin")
    public String loginMember(@RequestParam String userName, @RequestParam String password, Model model,
            HttpSession session) {
        Optional<Member> member = memberRepository.findByUserName(userName);
    
        if (member.isPresent() 
            && member.get().getUserName().equals(userName)
            && member.get().getPassword().equals(password)) 
        {
            session.setAttribute("loggedInMember", member.get());  // Store full member object
            session.setAttribute("memberId", member.get().getMemberId());  // Store only memberId
            return "redirect:/member/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "member/member_login";
        }
    }
    

    @GetMapping("/member/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        if (loggedInMember == null) {
            return "redirect:/member_login";
        }

        model.addAttribute("member", loggedInMember);
        return "member/member_dashboard";
    }

    

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/memberlogin";
    }
    


    // Display all members
    @GetMapping("/member/memberlist")
    public String listAllMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "member/allmemberlist"; // Display all members in a list format
    }


    
    //Search books by Id
    @GetMapping("/member/membersearch")
    public String searchmember(@RequestParam("query") Integer query, Model model) {
        List<Member> members = memberRepository.findByMemberId(query); 
    
        if (members.isEmpty()) {
            model.addAttribute("error", "No members found for: " + query);
        }
        model.addAttribute("members", members);
        model.addAttribute("query", query);
        return "member/membersearchresult";  // Use appropriate view for member search results
    }
    


    // Delete a member
    @GetMapping("/member/delete/{memberId}")
    public String deletemember(@PathVariable Integer memberId) {

        memberRepository.deleteById(memberId);
        return "member/memberremovemessage";
    }


    @GetMapping(path = "/member/all")
    public @ResponseBody Iterable<Member> getAllUsers() {
        return memberRepository.findAll();
    }
}
