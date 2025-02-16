// package com.example.librarymanagement.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// import com.example.librarymanagement.model.Member;
// import com.example.librarymanagement.repository.MemberRepository;

// @Controller
// public class MemberController {

//     private final MemberRepository memberRepository;

//     @Autowired
//     public MemberController(MemberRepository memberRepository) {
//         this.memberRepository = memberRepository;
//     }

//     @GetMapping(path = "/memberform")
//     public String inputform(Member member) {
//         return "member_form";  // This will pre-populate the form fields if admin is provided.
//     }

//     @PostMapping(path = "/member/save")
//     public String addNewUser(Member member) { 
//         try{
//             memberRepository.save(member);
//             return "redirect:/member/success";  // Redirect to success page if the form is submitted successfully
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "error";  // Redirect to error page if an exception occurs
//         }
        
//     }

//     @GetMapping(path = "/member/success")
//     public String showSuccessPage() {
//         return "result1"; // Success page after form submission
//     }

//     @GetMapping(path = "/member/all")
//     public @ResponseBody Iterable<Member> getAllUsers() {
//         return memberRepository.findAll();
//     }
// }


package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;


import com.example.librarymanagement.model.Member;
import com.example.librarymanagement.repository.MemberRepository;

@Controller
@RequestMapping(path="/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping(path = "/")
    public String inputform(Model member) {
        member.addAttribute("member", new Member());
        return "member_form"; 
    }

    @PostMapping(path = "/")
    public String addNewUser(@Valid @ModelAttribute("member") Member member, BindingResult result, Model model) { 
        if (result.hasErrors()) {
            return "member_form";
        }
        memberRepository.save(member);
        model.addAttribute("successMessage", "Congratulations! You have successfully logged in.");
        return "redirect:/member/success";
        
    }

    @GetMapping(path = "/member/success")
    public String showSuccessPage() {
        return "result1"; // Success page after form submission
    }

}

