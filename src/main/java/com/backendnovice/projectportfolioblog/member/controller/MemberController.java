package com.backendnovice.projectportfolioblog.member.controller;

import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    private final MemberService memberService;
    
    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }
    
    @GetMapping("/register")
    public String registerForm() {
        return "/register";
    }
    
    @PostMapping("/login")
    public String loginProcess(MemberDTO memberDTO) {
        return "/login";
    }
    
    @PostMapping("/register")
    public String registerProcess(MemberDTO memberDTO) {
        memberService.memberRegister(memberDTO);
        
        return "/register";
    }
    
}
