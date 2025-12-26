package com.example.demo.service;

import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;

import java.util.List;

public interface AdminService {

    UserResponseDto updateUser(Long id, UserResponseDto dto);
    UserResponseDto updateUserStatus(Long id, String status);
    List<UserResponseDto> getAllUsers();
    void changeRole(Long id, String role);
    UserResponseDto getUserById(Long id);
    List<User> searchUsers(String firstName, String lastName, Role role, Status status);
}
