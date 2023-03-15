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
    public boolean memberValidate(MemberDTO memberDTO) {
        MemberEntity member = memberRepository.findByEmail(memberDTO.getEmail()).get();
        
        if(passwordEncoder.matches(memberDTO.getPassword(), member.getPassword()))
            return true;
        else
            return false;
    }
}
