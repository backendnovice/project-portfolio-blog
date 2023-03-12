package com.backendnovice.projectportfolioblog.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name   : MemberDTO
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : DTO Class associated with Member Features.
**/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    
    private String id;
    
    private String email;
    
    private String password;
    
    private String name;
    
    private String phone;
    
}
