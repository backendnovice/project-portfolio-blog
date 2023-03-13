package com.backendnovice.projectportfolioblog.member.domain;

import com.backendnovice.projectportfolioblog.global.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @name   : MemberDetails
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Custom UserDetails for Member.
**/

@Getter
@Builder
@NoArgsConstructor
public class MemberDetails implements UserDetails {
    
    private MemberEntity member;
    
    public MemberDetails(MemberEntity member) {
        this.member = member;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        System.out.println(member.toString());
        
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        
        return authorities;
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
