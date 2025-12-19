//package com.example.demo.config;
//
//import com.example.demo.security.JwtUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//public class FilterConfig {
//
//    private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//
//    public FilterConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public JwtAuthenticateFilter jwtAuthenticateFilter() {
//        return new JwtAuthenticateFilter(jwtUtil, userDetailsService);
//    }
//}
