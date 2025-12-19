package com.example.demo.mapper;

import com.example.demo.dto.CourseRequestDto;
import com.example.demo.dto.CourseResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    public static Course toEntity(CourseRequestDto dto) {
        Course course = Course.builder()
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        return course;
    }

    public static CourseResponseDto toDto(Course course) {
        if (course == null) return null;

        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());

        if (course.getProfessor() != null) {
            dto.setProfessor(UserMapper.toDto(course.getProfessor()));
        }

        if (course.getStudents() != null) {
            List<UserResponseDto> studentDtos = new ArrayList<>();
            for (User student : course.getStudents()) {
                studentDtos.add(UserMapper.toDto(student));
            }
            dto.setStudents(studentDtos);
        }

        return dto;
    }



    public static List<CourseResponseDto> toDtoList(List<Course> courses) {
        List<CourseResponseDto> dtoList = new ArrayList<>();
        for (Course course : courses) {
            dtoList.add(toDto(course));
        }
        return dtoList;
    }

}
