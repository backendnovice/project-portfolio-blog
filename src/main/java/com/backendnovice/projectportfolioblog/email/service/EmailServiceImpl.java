package com.backendnovice.projectportfolioblog.email.service;

import com.backendnovice.projectportfolioblog.email.dto.EmailDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    private JavaMailSender emailSender;
    
    @Override
    public String sendRegisterMail(EmailDTO emailDTO) {
        String randomNumber = generateRandomNumber();
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom("backendnovice@gmail.com");
        message.setTo(emailDTO.getEmail());
        message.setSubject("Welcome to Portfolio-Blog");
        message.setText(makeMailText(randomNumber));
        
        emailSender.send(message);
        
        return passwordEncoder.encode(randomNumber);
    }
    
    @Override
    public boolean validateRandomNumber(EmailDTO emailDTO) {
        return passwordEncoder.matches(emailDTO.getNumberDecrypt(), emailDTO.getNumberEncrypt());
    }
    
    private String generateRandomNumber() {
        Random random = new Random();
        
        return String.valueOf(random.nextInt(888888) + 111111);
    }
    
    private String makeMailText(String number) {
        String text = "<h1>Welcome to Register portfolio-project!</h1>"
                + "<p>This is your Register Validation Code</p>"
                + "<b>" + number + "</b>";
        return text;
    }
}
