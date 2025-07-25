/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 *
 * @author LENOVO
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.scm.controllers",
    "com.scm.repositories",
    "com.scm.services"
})
public class SpringSecurityConfigs {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf
        (c -> c.disable()).authorizeHttpRequests(
                requests 
                -> requests.requestMatchers("/", "/home").authenticated()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/api/suppliers/**").hasAnyRole("ADMIN", "QuanLyNhaCungCap")
                        .requestMatchers("/api/khohang/**").hasAnyRole("ADMIN", "QuanLyKho")
                        .requestMatchers("/api/quanlykho/**").hasAnyRole("ADMIN", "QuanLyKho")
                        .requestMatchers("/api/donhang/**").hasAnyRole("ADMIN", "QuanLyDonHang")
                        .requestMatchers("/api/vanchuyen/**").hasAnyRole("ADMIN", "QuanLyVanChuyen")
//                        .requestMatchers("/api/dtvc/**").hasAnyRole("ADMIN", "QuanLyVanChuyen")
                        .requestMatchers("/api/cskh/**").hasAnyRole("ADMIN", "CSKH"))
                .formLogin(form -> form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll());
        
        return http.build();
    }
}
