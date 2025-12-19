package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
    private String title;
    private User professor;
    private LocalDate startDate;
    private LocalDate endDate;
}
