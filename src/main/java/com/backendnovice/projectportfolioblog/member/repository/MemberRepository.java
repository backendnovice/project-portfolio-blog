package com.backendnovice.projectportfolioblog.member.repository;

import com.backendnovice.projectportfolioblog.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @name   : MemberRepository
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Register Beans that exchange Data with Member table.
**/

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    
    Optional<MemberEntity> findByEmail(@Param("m_email") String email);
    
    Optional<MemberEntity> findByEmailAndPassword(@Param("m_email") String email, @Param("m_password") String password);
    
    boolean existsByEmail(@Param("m_email") String email);
    
}
