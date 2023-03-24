package com.backendnovice.projectportfolioblog.email.service;

import com.backendnovice.projectportfolioblog.email.dto.EmailDTO;

public interface EmailService {
    String sendRegisterMail(EmailDTO emailDTO);
    
    boolean validateRandomNumber(EmailDTO emailDTO);
}
