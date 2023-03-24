package com.backendnovice.projectportfolioblog.member.controller;

import com.backendnovice.projectportfolioblog.email.dto.EmailDTO;
import com.backendnovice.projectportfolioblog.email.service.EmailService;
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
    
    @Autowired
    private EmailService emailService;

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
    
    @PostMapping("/register/email/generate")
    public ResponseEntity<Map<String, String>> sendEmailAPI(@RequestBody EmailDTO emailDTO) {
        Map<String, String> response = new HashMap<>();
        
        String numberGenerated = emailService.sendRegisterMail(emailDTO);
        
        response.put("numberGenerated", numberGenerated);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register/email/validate")
    public ResponseEntity<Map<String, Boolean>> validateEmailAPI(@RequestBody EmailDTO emailDTO) {
        Map<String, Boolean> response = new HashMap<>();
        
        boolean isVerified = emailService.validateRandomNumber(emailDTO);
        
        response.put("isVerified", isVerified);
        
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
