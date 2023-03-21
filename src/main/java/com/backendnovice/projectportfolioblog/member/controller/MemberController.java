package com.backendnovice.projectportfolioblog.member.controller;

import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String mappingLoginForm() {
        return "/member/login";
    }
    
    @GetMapping("/register")
    public String mappingRegisterForm() {
        return "/member/register";
    }
    
    @GetMapping("/profile")
    public String mappingProfileForm() {
        return "/member/profile";
    }
    
    @GetMapping("/support/password")
    public String mappingChangePasswordForm() {
        return "/member/support/password";
    }
    
    @PostMapping("/register")
    public String registerProcess(MemberDTO memberDTO) {
        memberService.memberRegister(memberDTO);
        
        return "/member/login";
    }
    
    @PostMapping("/support/password")
    public String changePasswordProcess(MemberDTO memberDTO, Principal principal) {
        memberDTO.setEmail(principal.getName());
        
        memberService.memberChangePassword(memberDTO);
        
        return "redirect:/member/logout";
    }
    
    @GetMapping("/support/withdraw")
    public String withdrawProcess(Principal principal) {
        memberService.memberWithdraw(principal.getName());
        
        SecurityContextHolder.clearContext();
        
        return "redirect:/member/logout";
    }
    
}
