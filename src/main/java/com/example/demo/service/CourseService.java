package com.example.demo.service;

import com.example.demo.dto.course.CourseDetailsDto;
import com.example.demo.dto.course.CourseRequestDto;
import com.example.demo.dto.course.CourseResponseDto;
import com.example.demo.dto.user.UserResponseDto;

import java.util.List;

public interface CourseService {
    List<CourseResponseDto> findAllCourse();
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);
    void deleteCourse(Long id);
    void updateCourse(Long id, CourseRequestDto request);
    CourseResponseDto addProfessor(Long courseId, Long professorId);
    CourseDetailsDto getCourseDetailsWithProfessors(Long courseId);
    List<UserResponseDto> getAllProfessors();
    CourseResponseDto removeProfessor(Long courseId, Long professorId);
    List<UserResponseDto> getStudents(Long courseId);
    CourseResponseDto addStudent(Long courseId, Long studentId);
    CourseResponseDto removeStudent(Long courseId, Long studentId);
    CourseResponseDto findCourseById(Long courseId);
    List<CourseResponseDto> getCoursesByProfessor(Long id);
}
