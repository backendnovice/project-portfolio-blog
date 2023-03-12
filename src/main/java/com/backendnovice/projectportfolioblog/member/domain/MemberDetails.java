package com.backendnovice.projectportfolioblog.member.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @name   : MemberDetails
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Custom UserDetails for Member.
**/

@Getter
@Builder
public class MemberDetails implements UserDetails {
    
    private MemberEntity member;
    
    public MemberDetails(MemberEntity member) {
        this.member = member;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString()));
    }
    
    @Override
    public String getUsername() {
        return member.getEmail();
    }
    
    @Override
    public String getPassword() {
        return member.getPassword();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
