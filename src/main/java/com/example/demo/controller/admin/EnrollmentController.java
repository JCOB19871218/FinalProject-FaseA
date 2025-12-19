package com.example.demo.controller.admin;

import com.example.demo.dto.CourseResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final CourseService courseService;

    @PostMapping("/add-professor/{courseId}/professors/{professorId}")
    public ResponseEntity<?> addProfessorToCourse(@PathVariable Long courseId, @PathVariable Long professorId) {
        CourseResponseDto course = courseService.addProfessor(courseId, professorId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete-professor/{courseId}/professors/{professorId}")
    public ResponseEntity<?> removeProfessorFromCourse(@PathVariable Long courseId, @PathVariable Long professorId) {
        CourseResponseDto course = courseService.removeProfessor(courseId, professorId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/get-student/{courseId}/students/requests")
    public ResponseEntity<?> getStudents(@PathVariable Long courseId) {
        List<UserResponseDto> students = courseService.getStudents(courseId);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/add-student/{courseId}/students/{studentId}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        CourseResponseDto course = courseService.addStudent(courseId, studentId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete-student/{courseId}/students/{studentId}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        CourseResponseDto course = courseService.removeStudent(courseId, studentId);
        return ResponseEntity.ok(course);
    }
}
