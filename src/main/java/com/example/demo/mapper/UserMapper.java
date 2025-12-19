package com.example.demo.mapper;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        User user = User.builder().username(dto.getUsername())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        return user;
    }

    public static UserResponseDto toDto(User user) {
        if (user == null) return null;

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        return dto;
    }


public static List<UserResponseDto> toDtoList(List<User> fetchAllUser) {
    List<UserResponseDto> dtoList = new ArrayList<>();
    for (User user : fetchAllUser) {
        if (user.getRole() == Role.PROFESSOR || user.getRole() == Role.STUDENT) {
            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setRole(user.getRole());
            dto.setStatus(user.getStatus());
            dtoList.add(dto);

        }
    }
    return dtoList;
}
}


