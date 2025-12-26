package com.example.demo.service.impl;

import com.example.demo.dto.example.ExampleRequestDto;
import com.example.demo.dto.example.ExampleResponseDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.example.Example;
import com.example.demo.entity.example.ExampleQuestion;
import com.example.demo.entity.example.Question;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.ExampleMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ExampleRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ExampleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<ExampleResponseDto> getExamsOfCourse(Long courseId, String username) {
        User professor = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        return ExampleMapper.toDtoList(exampleRepository.findByCourseIdAndProfessorId(courseId, professor.getId()));
    }

    @Override
    public ExampleResponseDto createExample(Long courseId, ExampleRequestDto dto, String username) {

        User professor = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        boolean isValidProfessorId = course.getProfessor().getId().equals(professor.getId());

        if (!isValidProfessorId) {
            throw new BusinessException("You are not professor of this course");
        }

        Example example = ExampleMapper.toEntity(dto);
        example.setCourse(course);
        example.setProfessor(professor);

        exampleRepository.save(example);
        return ExampleMapper.toDto(example);
    }

    @Override
    public ExampleResponseDto updateExample(Long examId, ExampleRequestDto dto, String username) {

        Example example = exampleRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));

        boolean isValidUsernameProfessor = example.getProfessor().getUsername().equals(username);

        if (!isValidUsernameProfessor) {
            throw new BusinessException("You cannot edit this exam");
        }

        example.setTitle(dto.getTitle());
        example.setDescription(dto.getDescription());
        example.setDuration(dto.getDuration());

        exampleRepository.save(example);
        return ExampleMapper.toDto(example);
    }

    @Override
    public void deleteExample(Long examId, String username) {

        Example example = exampleRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));

        boolean isValidUsername = example.getProfessor().getUsername().equals(username);

        if (!isValidUsername) {
            throw new BusinessException("You cannot delete this exam");
        }
        exampleRepository.delete(example);
    }


    @Override
    @Transactional
    public void addQuestionToExample(Long examId, Long questionId, User professor) {

        Example example = exampleRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));


        boolean isValidProfessor = example.getProfessor().getId().equals(professor.getId());
        boolean isValidQuestionOfProfessor = question.getProfessor().getId().equals(professor.getId());

        if (!isValidProfessor) {
            throw new RuntimeException("You are not owner of this example");
        }

        if(!isValidQuestionOfProfessor)
            throw new RuntimeException("You are not owner of this question");

        ExampleQuestion exampleQuestion = new ExampleQuestion();
        exampleQuestion.setExample(example);
        exampleQuestion.setQuestion(question);
        exampleQuestion.setDefaultScore(1);

        example.getQuestions().add(exampleQuestion);
    }

    @Override
    public List<ExampleResponseDto> getExamsByProfessorAndCourse(Long id, Long courseId) {
        return ExampleMapper.toDtoList(exampleRepository.findByCourseIdAndProfessorId(courseId,id));
    }


}
