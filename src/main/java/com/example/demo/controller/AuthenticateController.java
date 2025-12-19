//package com.example.demo.controller;
//
//import com.example.demo.dto.UserLoginRequestDto;
//import com.example.demo.security.CustomUserDetails;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class AuthenticateController {
//
//    private final AuthenticationManager authManager;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto request) {
//        try {
//            Authentication auth = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//
//            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//            String token = jwtUtil.generateToken(userDetails);
//
//            // ارسال مسیر redirect بر اساس نقش
//            String redirectUrl;
//            switch (userDetails.getRole()) {
//                case "ADMIN" -> redirectUrl = "/dashboard-admin.html";
//                case "PROFESSOR" -> redirectUrl = "/dashboard-professor.html";
//                case "STUDENT" -> redirectUrl = "/dashboard-student.html";
//                default -> redirectUrl = "/login.html";
//            }
//
//
//            return ResponseEntity.ok(Map.of(
//                    "token", token,
//                    "redirect", redirectUrl
//            ));
//
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Invalid username or password");
//        }
//    }
//}
