//package com.example.demo.service.impl;
//
//import com.example.demo.dto.question.QuestionReposeDto;
//import com.example.demo.entity.User;
//import com.example.demo.entity.example.Example;
//import com.example.demo.entity.example.ExampleQuestion;
//import com.example.demo.entity.example.Question;
//import com.example.demo.exception.BusinessException;
//import com.example.demo.mapper.QuestionMapper;
//import com.example.demo.repository.ExampleQuestionRepository;
//import com.example.demo.repository.ExampleRepository;
//import com.example.demo.repository.QuestionRepository;
//import com.example.demo.service.ExampleQuestionService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ExampleQuestionServiceImpl implements ExampleQuestionService {
//
//    private final ExampleRepository exampleRepository;
//    private final QuestionRepository questionRepository;
//    private final ExampleQuestionRepository exampleQuestionRepository;
//
//    @Transactional
//    @Override
//    public void addQuestionToExam(Long examId, Long questionId, Integer defaultScore, User professor) {
//        Example example = exampleRepository.findById(examId)
//                .orElseThrow(() -> new BusinessException("Exam not found"));
//
//        if (!example.getProfessor().getId().equals(professor.getId())) {
//            throw new BusinessException("You are not owner of this exam");
//        }
//
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new BusinessException("Question not found"));
//
//        if (!question.getProfessor().getId().equals(professor.getId())) {
//            throw new BusinessException("You are not owner of this question");
//        }
//
//        if (exampleQuestionRepository.existsByExampleIdAndQuestionId(examId, questionId)) {
//            throw new BusinessException("Question already added to this exam");
//        }
//
//        ExampleQuestion exampleQuestion = ExampleQuestion.builder()
//                .example(example)
//                .question(question)
//                .defaultScore(defaultScore)
//                .build();
//
//        example.getQuestions().add(exampleQuestion);
//        exampleRepository.save(example);
//    }
//
//    @Transactional
//    @Override
//    public void updateDefaultScore(Long exampleQuestionId, Integer newScore, User professor
//    ) {
//        ExampleQuestion exampleQuestion = exampleQuestionRepository.findById(exampleQuestionId)
//                .orElseThrow(() -> new BusinessException("Exam question not found"));
//
//        if (!exampleQuestion.getExample().getProfessor().getId().equals(professor.getId())) {
//            throw new BusinessException("You are not owner of this exam");
//        }
//
//        exampleQuestion.setDefaultScore(newScore);
//    }
//
//    @Transactional
//    @Override
//    public void removeQuestionFromExam(Long exampleQuestionId, User professor) {
//        ExampleQuestion exampleQuestion = exampleQuestionRepository.findById(exampleQuestionId)
//                .orElseThrow(() -> new BusinessException("Exam question not found"));
//
//        if (!exampleQuestion.getExample().getProfessor().getId().equals(professor.getId())) {
//            throw new BusinessException("You are not owner of this exam");
//        }
//
//        exampleQuestionRepository.delete(exampleQuestion);
//    }
//
//    @Override
//    public List<QuestionReposeDto> getExamQuestions(Long examId, User professor) {
//        Example exam = exampleRepository.findById(examId)
//                .orElseThrow();
//
//        return exam.getQuestions()
//                .stream()
//                .map(eq -> {
//                    QuestionReposeDto dto = QuestionMapper.toDto(eq.getQuestion());
//                    dto.setDefaultScore(eq.getDefaultScore());
//                    return dto;
//                })
//                .toList();
//    }
//
//    @Override
//    public Integer calculateTotalScore(Long examId, User professor) {
//        Example exam = exampleRepository.findById(examId)
//                .orElseThrow();
//
//        return exam.getQuestions()
//                .stream()
//                .mapToInt(ExampleQuestion::getDefaultScore)
//                .sum();
//    }
//}
