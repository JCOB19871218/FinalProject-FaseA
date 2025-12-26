//package com.example.demo.service;
//
//import com.example.demo.dto.question.QuestionReposeDto;
//import com.example.demo.entity.User;
//
//import java.util.List;
//
//public interface ExampleQuestionService {
//    void addQuestionToExam(Long examId, Long questionId, Integer defaultScore, User professor);
//
//    void updateDefaultScore(Long exampleQuestionId, Integer newScore, User professor);
//
//    void removeQuestionFromExam(Long exampleQuestionId, User professor);
//
//    List<QuestionReposeDto> getExamQuestions(Long examId, User professor);
//
//    Integer calculateTotalScore(Long examId, User professor);
//}
