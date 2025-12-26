package com.example.demo.controller.example;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.example.ExampleRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.ExampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professor/exams")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PROFESSOR')")
public class ExampleController {
    private final ExampleService exampleService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseExams(@PathVariable Long courseId, Authentication auth) {
        return ResponseEntity.ok(exampleService.getExamsOfCourse(courseId, auth.getName()));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> createExample(@PathVariable Long courseId,
                                        @Valid @RequestBody ExampleRequestDto dto,
                                        Authentication auth) {
        return ResponseEntity.ok(exampleService.createExample(courseId, dto, auth.getName()));
    }

    @PutMapping("/{examId}")
    public ResponseEntity<?> updateExample(@PathVariable Long examId,
                                        @Valid @RequestBody ExampleRequestDto dto,
                                        Authentication auth) {
        return ResponseEntity.ok(exampleService.updateExample(examId, dto, auth.getName()));
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<?> deleteExample(@PathVariable Long examId, Authentication auth) {
        exampleService.deleteExample(examId, auth.getName());
        return ResponseEntity.ok("Exam deleted");
    }

    @PostMapping("/{examId}/questions/{questionId}")
    public void addQuestionToExample(@PathVariable Long examId, @PathVariable Long questionId) {
        User professor = getLoggedInProfessor();
        exampleService.addQuestionToExample(examId, questionId, professor);
    }


    private User getLoggedInProfessor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        assert userDetails != null;
        return userDetails.getUser();
    }

}
