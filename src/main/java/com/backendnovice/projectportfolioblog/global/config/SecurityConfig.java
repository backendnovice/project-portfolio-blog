package com.backendnovice.projectportfolioblog.global.config;

import com.backendnovice.projectportfolioblog.member.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
    
    private static final String[] LINKS_USER = {"/blog/**", "/member/profile", "/member/api/password", "/member/support/**", "/member/logout"};
    
    private static final String[] LINKS_ADMIN = {"/member/admin/**", "/member/support/**"};
    
    private static final String[] LINKS_PUBLIC = {"/member/login", "/member/api/login", "/member/register", "/member/api/register", "/blog/home"};
    
    private static final String[] LINKS_RESOURCE = {"/layout/**", "/css/**", "/js/**"};
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(LINKS_USER).hasRole("USER")
                        .requestMatchers(LINKS_ADMIN).hasRole("ADMIN")
                        .requestMatchers(LINKS_PUBLIC).anonymous()
                        .requestMatchers(LINKS_RESOURCE).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/member/login")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/blog/home");
                        })
                        .failureUrl("/member/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/member/logout")
                        .logoutSuccessUrl("/member/login")
                        .permitAll()
                );
    
        httpSecurity
                .httpBasic().disable()
                .csrf().disable();
        
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
