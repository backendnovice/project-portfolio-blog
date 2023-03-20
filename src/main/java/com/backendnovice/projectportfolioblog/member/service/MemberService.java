package com.backendnovice.projectportfolioblog.member.service;

import com.backendnovice.projectportfolioblog.global.enums.Role;
import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import com.backendnovice.projectportfolioblog.member.dto.MemberDTO;

/**
 * @name   : MemberService
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Defines the Methods required for Member features.
**/

public interface MemberService {
    
    void memberRegister(MemberDTO memberDTO);
    
    void memberChangePassword(MemberDTO memberDTO);
    
    void memberWithdraw(String email);
    
    boolean validateLoginAPI(MemberDTO memberDTO);
    
    boolean validateRegisterAPI(MemberDTO memberDTO);
    
    boolean validatePasswordAPI(MemberDTO memberDTO);
    
    default MemberEntity dtoToEntity(MemberDTO memberDTO) {
        MemberEntity member = MemberEntity.builder()
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .name(memberDTO.getName())
                .phone(memberDTO.getPhone())
                .role(Role.ROLE_USER)
                .build();
        
        return member;
    }
    
    default MemberDTO entityToDTO(MemberEntity member) {
        MemberDTO memberDTO = MemberDTO.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .build();
        
        return memberDTO;
    }
    
}
