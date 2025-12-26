package com.example.demo.dto.user;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;

}
