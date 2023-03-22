package com.backendnovice.projectportfolioblog.member.service;

import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name   : MemberServiceImpl
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Implement MemberService to provide necessary features.
 **/
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public String register(MemberDTO memberDTO) {
        String password = memberDTO.getPassword();
        
        if(checkPasswordPattern(password)) {
            memberDTO.setPassword(passwordEncoder.encode(password));
            
            memberRepository.save(dtoToEntity(memberDTO));
            
            return "redirect:/member/login";
        }
        
        return "redirect:/member/register";
    }
    
    @Override
    public String changePassword(MemberDTO memberDTO) {
        String email = memberDTO.getEmail();
        String password = memberDTO.getPassword();
        
        if(checkPasswordPattern(password)) {
            MemberEntity member = memberRepository.findByEmail(email).get();
            
            member.setEmail(passwordEncoder.encode(password));
            
            memberRepository.save(member);
            
            return "redirect:/member/logout";
        }
        
        return "redirect:/member/profile";
    }
    
    @Override
    @Transactional
    public String withdraw(String email) {
        memberRepository.deleteByEmail(email);
    
        SecurityContextHolder.clearContext();
        
        return "redirect:/member/logout";
    }
    
    @Override
    public boolean validateLoginAPI(MemberDTO memberDTO) {
        MemberEntity member = memberRepository.findByEmail(memberDTO.getEmail()).get();
        
        if(passwordEncoder.matches(memberDTO.getPassword(), member.getPassword()))
            return true;
        else
            return false;
    }
    
    @Override
    public boolean validateRegisterAPI(MemberDTO memberDTO) {
        boolean isExists = memberRepository.existsByEmail(memberDTO.getEmail());
        
        return isExists;
    }
    
    @Override
    public boolean validatePasswordAPI(MemberDTO memberDTO) {
        MemberEntity member = memberRepository.findByEmail(memberDTO.getEmail()).get();
        
        boolean isMatch = passwordEncoder.matches(memberDTO.getPassword(), member.getPassword());
        
        return isMatch;
    }
    
    private boolean checkPasswordPattern(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        
        Matcher matcher = pattern.matcher(password);
        
        if(!matcher.find())
            return false;
        else
            return true;
    }
}
