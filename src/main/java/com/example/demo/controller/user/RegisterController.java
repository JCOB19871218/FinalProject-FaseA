package com.example.demo.controller.user;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }
}
