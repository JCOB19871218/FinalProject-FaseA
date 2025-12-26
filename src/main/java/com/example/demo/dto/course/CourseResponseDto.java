package com.example.demo.dto.course;

import com.example.demo.dto.user.UserResponseDto;
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
