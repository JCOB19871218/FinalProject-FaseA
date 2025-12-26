package com.example.demo.dto.course;

import com.example.demo.dto.user.UserResponseDto;
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
