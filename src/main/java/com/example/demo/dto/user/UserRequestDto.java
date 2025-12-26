package com.example.demo.dto.user;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto{
   private String username;
    private String firstName;
    private String lastName;
    private String password;

}
