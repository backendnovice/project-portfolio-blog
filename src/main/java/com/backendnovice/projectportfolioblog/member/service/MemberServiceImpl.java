package com.backendnovice.projectportfolioblog.member.service;

import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
    
    private final MemberRepository memberRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final MemberDetailsService memberDetailsService;
    
    
    @Override
    public void memberRegister(MemberDTO memberDTO) {
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        
        memberRepository.save(dtoToEntity(memberDTO));
    }
    
}
