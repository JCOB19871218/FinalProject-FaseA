package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void addProfessor(Long courseId, Long professorId) {
        User professor = userRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setProfessor(professor);
        courseRepository.save(course);
    }

    @Override
    public void addStudent(Long courseId, Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .student(student)
                .enrollmentDate(LocalDate.now())
                .build();
        enrollmentRepository.save(enrollment);
    }
}
