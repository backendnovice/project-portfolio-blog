package com.backendnovice.projectportfolioblog.board.domain;

import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private Long id;
    
    @Column(name = "b_title")
    private String title;
    
    @Column(name = "b_content")
    private String content;
    
    @Column(name = "b_count")
    private int count = 0;
    
    @Column(name = "b_author")
    private String author;
    
    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "b_member")
    private MemberEntity member;
}
