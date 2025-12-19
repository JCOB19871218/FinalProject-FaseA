package com.example.demo.service;

import com.example.demo.dto.UserResponseDto;
import java.util.List;

public interface AdminService {

    UserResponseDto updateUser(Long id, UserResponseDto dto);
    UserResponseDto updateUserStatus(Long id, String status);
    List<UserResponseDto> getAllUsers();
    void changeRole(Long id, String role);
    UserResponseDto getUserById(Long id);

}
