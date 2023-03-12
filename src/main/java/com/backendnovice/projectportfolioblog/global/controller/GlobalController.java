package com.backendnovice.projectportfolioblog.global.controller;

import com.backendnovice.projectportfolioblog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @name   : GlobalController
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Global Mapping Controller.
**/

@Controller
@RequiredArgsConstructor
public class GlobalController {
    
    private final MemberService memberService;
    
    @GetMapping({"/", "/home"})
    public String homeForm() {
        return "home";
    }
    
}
