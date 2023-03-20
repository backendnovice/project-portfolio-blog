package com.backendnovice.projectportfolioblog.member.controller;

import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @name   : MemberController
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Member Mapping Controller.
**/

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
   
    @Autowired
    private MemberService memberService;
    
    @GetMapping("/login")
    public String loginForm() {
        return "/member/login";
    }
    
    @GetMapping("/register")
    public String registerForm() {
        return "/member/register";
    }
    
    @PostMapping("/register")
    public String registerProcess(MemberDTO memberDTO) {
        memberService.memberRegister(memberDTO);
        
        return "/member/login";
    }
    
    @GetMapping("/modify")
    public String modifyForm() {
        return "/member/modify";
    }
    
    @GetMapping("/help/password")
    public String changePasswordForm() {
        return "/member/help/password";
    }
    
    @PostMapping("/help/password")
    public String changePasswordProcess(MemberDTO memberDTO, Principal principal) {
        memberDTO.setEmail(principal.getName());
        
        memberService.memberChangePassword(memberDTO);
        
        return "redirect:/member/logout";
    }
    
    @GetMapping("/help/withdraw")
    public String withdrawForm() {
        return "/member/help/withdraw";
    }
    
}
