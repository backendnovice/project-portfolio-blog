package com.backendnovice.projectportfolioblog.repository;

import com.backendnovice.projectportfolioblog.global.enums.Role;
import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;
import com.backendnovice.projectportfolioblog.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberRepositoryTests {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void insertMembersTest() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .email("member@email.com")
                .password(passwordEncoder.encode("password"))
                .name("개발자")
                .phone("010-1234-5678")
                .role(Role.ROLE_USER)
                .build();
        
        memberRepository.save(member);
    }
    
    @Test
    public void PasswordMatchTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("member@email.com")
                .password("password")
                .build();
        
        MemberEntity member = memberRepository.findByEmail(memberDTO.getEmail()).get();
        
        if(passwordEncoder.matches(memberDTO.getPassword(), member.getPassword()))
            System.out.println("Good Password");
        else
            System.out.println("Bad Password");
    }
    
}
