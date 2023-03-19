package com.backendnovice.projectportfolioblog.member.controller;

import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member/api")
public class MemberRestController {
    
    @Autowired
    private MemberService memberService;
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> provideLoginAPI(@RequestBody MemberDTO memberDTO) {
        Map<String, Boolean> response = new HashMap<>();
    
        boolean isValid = memberService.validateLoginAPI(memberDTO);
        
        response.put("isValid", isValid);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> provideRegisterAPI(@RequestBody MemberDTO memberDTO) {
        Map<String, Boolean> response = new HashMap<>();
        
        boolean isExists = memberService.validateRegisterAPI(memberDTO);
        
        response.put("isExists", isExists);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/password")
    public ResponseEntity<Map<String, Boolean>> providePasswordAPI(@RequestBody MemberDTO memberDTO, Principal principal) {
        Map<String, Boolean> response = new HashMap<>();
        
        memberDTO.setEmail(principal.getName());
        
        boolean isMatch = memberService.validatePasswordAPI(memberDTO);
        
        response.put("isMatch", isMatch);
        
        return ResponseEntity.ok(response);
    }
}
