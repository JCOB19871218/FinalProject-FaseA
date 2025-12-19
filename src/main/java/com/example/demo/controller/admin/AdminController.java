package com.example.demo.controller.admin;

import com.example.demo.dto.UpdateStatusRequestDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        List<UserResponseDto> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponseDto user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/update-status/{id}")
    public UserResponseDto updateStatus(@PathVariable Long id,
                                        @RequestBody UpdateStatusRequestDto request) {
        return adminService.updateUserStatus(id, request.getStatus().name());
    }

    @PostMapping("/update-user/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody UserResponseDto dto) {
        return adminService.updateUser(id, dto);
    }





}
