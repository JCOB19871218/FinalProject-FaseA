package com.example.demo.controller.user;

import com.example.demo.dto.course.CourseResponseDto;
import com.example.demo.dto.example.ExampleResponseDto;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.ExampleService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PROFESSOR')")
public class ProfessorController {

    private final ExampleService exampleService;
    private final UserService userService;
    private  final CourseService courseService;

    private User getApprovedProfessor(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String username = authentication.getName();
        User professor = userService.getUserByUsername(username);

        if (professor == null) {
            throw new RuntimeException("Professor not found");
        }

        if (professor.getStatus() != Status.APPROVED) {
            throw new RuntimeException("Professor not approved");
        }

        return professor;
    }

    @GetMapping("/exams/course/{courseId}/list")
    public ResponseEntity<?> getExamsByCourse(
            @PathVariable Long courseId,
            Authentication authentication
    ) {
        try {
            User professor = getApprovedProfessor(authentication);
            List<ExampleResponseDto> exams = exampleService.getExamsByProfessorAndCourse(professor.getId(), courseId);
            return ResponseEntity.ok(exams);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getProfessorCourses(Authentication authentication) {
        try {
            User professor = getApprovedProfessor(authentication);
            List<CourseResponseDto> courses = courseService.getCoursesByProfessor(professor.getId());
            return ResponseEntity.ok(courses);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
