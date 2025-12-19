package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/student/**", "/api/student/**").hasRole("STUDENT")
                        .requestMatchers("/professor/**", "/api/professor/**").hasRole("PROFESSOR")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(roleBasedSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return (request, response, authentication) -> {
            var authorities = authentication.getAuthorities();

            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin/dashboard");
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
                response.sendRedirect("/student/dashboard");
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"))) {
                response.sendRedirect("/professor/dashboard");
            } else {
                response.sendRedirect("/login?error");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
