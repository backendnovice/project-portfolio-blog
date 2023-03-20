package com.backendnovice.projectportfolioblog.member.domain;

import com.backendnovice.projectportfolioblog.global.domain.DateEntity;
import com.backendnovice.projectportfolioblog.global.enums.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * @name   : Member
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Entity Class that Mapped with Member Table.
**/

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity extends DateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long id;
    
    @Column(name = "m_email", unique = true)
    private String email;
    
    @Column(name = "m_password")
    private String password;
    
    @Column(name = "m_name")
    private String name;
    
    @Column(name = "m_phone")
    private String phone;
    
    @Column(name = "m_role")
    @Enumerated(EnumType.STRING)
    private Role role;
    
}
