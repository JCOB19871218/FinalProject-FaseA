package com.example.demo.service;

import com.example.demo.dto.example.ExampleRequestDto;
import com.example.demo.dto.example.ExampleResponseDto;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ExampleService {
    List<ExampleResponseDto> getExamsOfCourse(Long courseId, String professorUsername);
    ExampleResponseDto createExample(Long courseId, ExampleRequestDto dto, String professorUsername);
    ExampleResponseDto updateExample(Long examId, ExampleRequestDto dto, String professorUsername);
    void deleteExample(Long examId, String professorUsername);

    @Transactional
    void addQuestionToExample(Long examId, Long questionId, User professor);

    List<ExampleResponseDto> getExamsByProfessorAndCourse(Long id, Long courseId);

}
