package com.backendnovice.projectportfolioblog.global.config;

import com.backendnovice.projectportfolioblog.member.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @name   : SecurityConfig
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Spring Security Settings.
**/

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private MemberDetailsService memberDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable();
        
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/board/**").hasRole("ROLE_USER")
                        .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                        .requestMatchers("/", "/member/**").permitAll()
                )
                .formLogin((login) -> login
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        
        return httpSecurity.build();
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        provider.setUserDetailsService(memberDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
    }
    
}
