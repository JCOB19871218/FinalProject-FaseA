package com.example.demo.service;

import com.example.demo.dto.question.QuestionReposeDto;
import com.example.demo.dto.question.QuestionRequestDto;
import com.example.demo.entity.User;
import com.example.demo.entity.example.ExampleQuestion;
import com.example.demo.entity.example.Question;
import jakarta.transaction.Transactional;

import java.util.List;

public interface QuestionService {


    @Transactional
    QuestionReposeDto addToBank(QuestionRequestDto dto, User professor);

    List<QuestionReposeDto> getBankQuestions(Long professorId);
}
