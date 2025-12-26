package com.example.demo.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginRequestDto {
    @NonNull
    private String username;
    @NotBlank
    @Size( min=6 , max = 12 , message = "message of password")
    private String password;
}
