package com.backendnovice.projectportfolioblog.member.service;

import com.backendnovice.projectportfolioblog.member.domain.MemberDetails;
import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import com.backendnovice.projectportfolioblog.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @name   : MemberDetailsService
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Implement UserDetailsService to return UserDetails required for login.
**/

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    
    private final MemberRepository memberRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Member"));
        
        return new MemberDetails(member);
    }
    
}
