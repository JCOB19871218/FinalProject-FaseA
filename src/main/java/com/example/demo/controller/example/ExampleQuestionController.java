//package com.example.demo.controller.question;
//
//import com.example.demo.config.CustomUserDetails;
//import com.example.demo.dto.question.QuestionReposeDto;
//import com.example.demo.entity.User;
//import com.example.demo.service.ExampleQuestionService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/exams")
//@RequiredArgsConstructor
//public class ExampleQuestionController {
//
//    private final ExampleQuestionService exampleQuestionService;
//
//    @PostMapping("/{examId}/questions")
//    public void addQuestionToExam(
//            @PathVariable Long examId,
//            @RequestParam Long questionId,
//            @RequestParam Integer defaultScore
//    ) {
//        User professor = getLoggedInProfessor();
//        exampleQuestionService.addQuestionToExam(
//                examId,
//                questionId,
//                defaultScore,
//                professor
//        );
//    }
//
//    @GetMapping("/{examId}/questions")
//    public List<QuestionReposeDto> getExamQuestions(
//            @PathVariable Long examId
//    ) {
//        User professor = getLoggedInProfessor();
//        return exampleQuestionService.getExamQuestions(examId, professor);
//    }
//
//    private User getLoggedInProfessor() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        assert auth != null;
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//        assert userDetails != null;
//        return userDetails.getUser();
//    }
//}
