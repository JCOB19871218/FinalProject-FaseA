package com.example.demo.util;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByUsername("admin").orElseGet(() -> {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .status(Status.APPROVED)
                    .build();
            return userRepository.save(admin);
        });
    }
}
