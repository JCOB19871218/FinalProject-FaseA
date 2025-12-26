package com.example.demo.controller.question;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.dto.question.QuestionReposeDto;
import com.example.demo.dto.question.QuestionRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.QuestionService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
        private final QuestionService questionService;
        private final UserService userService;

        @PostMapping
        public QuestionReposeDto createQuestion(@RequestBody QuestionRequestDto dto) {
            User professor = getLoggedInProfessor();
            return questionService.addToBank(dto, professor);
        }

        @GetMapping("/bank")
        public List<QuestionReposeDto> getBankQuestions(
                @RequestParam Long courseId
        ) {
            User professor = getLoggedInProfessor();
            return questionService.getBankQuestions(professor.getId());
        }

        private User getLoggedInProfessor() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth == null || !auth.isAuthenticated()) {
                throw new RuntimeException("User not authenticated");
            }

            String username = auth.getName();
            User professor = userService.getUserByUsername(username);
            if(professor == null) throw new RuntimeException("Professor not found");

            return professor;
        }
    }


