package com.backendnovice.projectportfolioblog.member.service;

import com.backendnovice.projectportfolioblog.member.domain.MemberDetails;
import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void memberRegister(MemberDTO memberDTO) {
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        
        memberRepository.save(dtoToEntity(memberDTO));
    }
    
    @Override
    public void memberChangePassword(MemberDTO memberDTO) {
        MemberEntity member = memberRepository.findByEmail(memberDTO.getEmail()).get();
        
        member.builder().password(passwordEncoder.encode(memberDTO.getPassword())).build();
        
        memberRepository.save(member);
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
}
