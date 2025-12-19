package com.example.demo.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsDto {
    private CourseResponseDto course;
    private List<UserResponseDto> allProfessors;
}
