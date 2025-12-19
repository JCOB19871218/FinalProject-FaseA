package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {
    private Long id;
    private String title;
    private UserResponseDto professor;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UserResponseDto> students = new ArrayList<>();

    public CourseResponseDto(Long id,String title, UserResponseDto professor, LocalDate startDate, LocalDate endDate) {
        this.id=id;
        this.title=title;
        this.professor=professor;
        this.startDate=startDate;
        this.endDate=endDate;
    }
}
