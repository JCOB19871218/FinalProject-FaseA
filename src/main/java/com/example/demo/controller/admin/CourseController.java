package com.example.demo.controller.admin;

import com.example.demo.dto.*;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create-courses")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequestDto dto) {
        CourseResponseDto course = courseService.createCourse(dto);
        return ResponseEntity.status(201).body(course);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses() {
        List<CourseResponseDto> courses = courseService.findAllCourse();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/get-professors")
    public ResponseEntity<?> getProfessors() {
        List<UserResponseDto> professors = courseService.getAllProfessors();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/courses/{courseId}/details")
    public ResponseEntity<?> getCourseDetails(@PathVariable Long courseId) {
        CourseDetailsDto details = courseService.getCourseDetailsWithProfessors(courseId);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        CourseResponseDto course = courseService.findCourseById(courseId);
        return ResponseEntity.ok(course);
    }


    @DeleteMapping("/delete-course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully");
    }


    @PutMapping("/update-course/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequestDto request) {
        courseService.updateCourse(courseId, request);
        return ResponseEntity.ok("Course updated successfully");
    }

}

