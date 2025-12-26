package com.example.demo.service;

import com.example.demo.dto.user.UserRegisterDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.entity.User;


public interface UserService {
    UserResponseDto registerUser(UserRegisterDto dto);

    User getUserByUsername(String username);
}
