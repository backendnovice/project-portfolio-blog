package com.backendnovice.projectportfolioblog.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @name   : DateEntity
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Common entity used by multiple entities.
**/

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateEntity {
    
    @CreatedDate
    @Column(name = "date_created", updatable = false)
    private LocalDateTime createdDate;
    
    @LastModifiedDate
    @Column(name = "date_modified")
    private LocalDateTime modifiedDate;
    
}
