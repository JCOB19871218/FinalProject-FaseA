package com.example.demo.service.impl;

import com.example.demo.dto.CourseDetailsDto;
import com.example.demo.dto.CourseRequestDto;
import com.example.demo.dto.CourseResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public List<CourseResponseDto> findAllCourse() {
        return CourseMapper.toDtoList(courseRepository.findAll());
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = Course.builder()
                .title(courseRequestDto.getTitle())
                .startDate(courseRequestDto.getStartDate())
                .endDate(courseRequestDto.getEndDate())
                .build();
        courseRepository.save(course);
        return CourseMapper.toDto(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(Long id, CourseRequestDto request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(request.getTitle());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        courseRepository.save(course);
    }


    @Override
    public CourseDetailsDto getCourseDetailsWithProfessors(Long courseId) {
        CourseResponseDto course = findCourseById(courseId);
        List<UserResponseDto> professors = getAllProfessors();

        CourseDetailsDto details = new CourseDetailsDto();
        details.setCourse(course);
        details.setAllProfessors(professors);
        return details;
    }

    @Override
    public List<UserResponseDto> getAllProfessors() {
        List<User> professors = userRepository.findByRole(Role.PROFESSOR);
        return UserMapper.toDtoList(professors);
    }

    @Override
    public CourseResponseDto addProfessor(Long courseId, Long professorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course not found"));
        User professor = userRepository.findById(professorId).orElseThrow(
                () -> new RuntimeException("Professor not found"));
        course.setProfessor(professor);
        courseRepository.save(course);
        return CourseMapper.toDto(course);
    }



    @Override
    public CourseResponseDto removeProfessor(Long courseId, Long professorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getProfessor() != null && course.getProfessor().getId().equals(professorId)) {
            course.setProfessor(null);
            courseRepository.save(course);
        }

        return new CourseResponseDto(course.getId(), course.getTitle(), null,
                course.getStartDate(), course.getEndDate());
    }

    @Override
    public List<UserResponseDto> getStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<User> students = course.getStudents();
        return UserMapper.toDtoList(students);
    }

    @Override
    public CourseResponseDto addStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
            courseRepository.save(course);
        }

        return CourseMapper.toDto(course);
    }

    @Override
    public CourseResponseDto removeStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        course.getStudents().remove(student);
        courseRepository.save(course);

        return CourseMapper.toDto(course);
    }

    @Override
    public CourseResponseDto findCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new EntityNotFoundException("course not found"));
        return CourseMapper.toDto(course);
    }

}
