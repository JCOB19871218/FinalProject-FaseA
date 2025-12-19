package com.example.demo.dto;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
