package com.backendnovice.projectportfolioblog.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String email;
    
    private String numberEncrypt;
    
    private String numberDecrypt;
}
