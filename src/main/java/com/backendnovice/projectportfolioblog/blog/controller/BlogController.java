package com.backendnovice.projectportfolioblog.blog.controller;

import com.backendnovice.projectportfolioblog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @name   : GlobalController
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Global Mapping Controller.
**/

@Controller
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    
    private final MemberService memberService;
    
    @GetMapping("/home")
    public String homeForm() {
        return "/blog/home";
    }
    
}
