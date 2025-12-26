package com.example.demo.service.impl;

import com.example.demo.dto.question.QuestionReposeDto;
import com.example.demo.dto.question.QuestionRequestDto;
import com.example.demo.entity.User;
import com.example.demo.entity.example.MultipleChoiceQuestion;
import com.example.demo.entity.example.Option;
import com.example.demo.entity.example.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;

    @Transactional
    @Override
    public QuestionReposeDto addToBank(QuestionRequestDto dto, User professor) {

        Question question;

        if (dto.getOptions() != null && !dto.getOptions().isEmpty()) {

            MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

            List<Option> options = dto.getOptions().stream()
                    .map(o -> {
                        Option option = new Option();
                        option.setText(o.getText());
                        option.setCorrect(o.isCorrect());
                        option.setQuestion(multipleChoiceQuestion);
                        return option;
                    })
                    .toList();

            multipleChoiceQuestion.setOptions(options);
            question = multipleChoiceQuestion;


            question.setQuestionText("");
        } else {

            question = new Question();
            question.setQuestionText(dto.getQuestionText());
        }

        question.setTitle(dto.getTitle());
        question.setProfessor(professor);

        return QuestionMapper.toDto(questionRepository.save(question));
    }

    @Override
    public List<QuestionReposeDto> getBankQuestions(Long professorId) {
        return QuestionMapper.toDtoList(
                questionRepository.findByProfessorId(professorId)
        );
    }
}
