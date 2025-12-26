package com.example.demo.dto.example;

import com.example.demo.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private User professor;
}
