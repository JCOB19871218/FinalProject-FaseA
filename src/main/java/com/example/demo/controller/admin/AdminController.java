package com.example.demo.controller.admin;

import com.example.demo.dto.user.UpdateStatusRequestDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/users/search")
    public List<User> search(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Status status
    ) {
        return adminService.searchUsers(firstName, lastName, role, status);
    }





}
