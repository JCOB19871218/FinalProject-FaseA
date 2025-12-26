package com.example.demo.mapper;

import com.example.demo.dto.question.QuestionRequestDto;
import com.example.demo.dto.question.QuestionReposeDto;
import com.example.demo.dto.question.OptionRequestDto;
import com.example.demo.entity.example.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {

    public static Question toEntity(QuestionRequestDto dto) {
        if (dto == null) return null;

        Question question;

        if (dto.getOptions() != null && !dto.getOptions().isEmpty()) {
            // Multiple Choice Question
            MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
            List<Option> options = new ArrayList<>();

            for (OptionRequestDto optDto : dto.getOptions()) {
                Option option = new Option();
                option.setText(optDto.getText());
                option.setCorrect(optDto.isCorrect());
                option.setQuestion(mcq);
                options.add(option);
            }

            mcq.setOptions(options);
            question = mcq;
        } else {
            question = new DescriptiveQuestion();
        }

        question.setTitle(dto.getTitle());
        question.setQuestionText(dto.getQuestionText());

        return question;
    }


    public static QuestionReposeDto toDto(Question question) {
        if (question == null) return null;

        QuestionReposeDto dto = new QuestionReposeDto();
        dto.setId(question.getId());
        dto.setTitle(question.getTitle());
        dto.setQuestionText(question.getQuestionText());

        if (question instanceof MultipleChoiceQuestion mcq) {
            List<OptionRequestDto> options = new ArrayList<>();
            for (Option option : mcq.getOptions()) {
                OptionRequestDto optDto = new OptionRequestDto();
                optDto.setText(option.getText());
                optDto.setCorrect(option.isCorrect());
                options.add(optDto);
            }
            dto.setOptions(options);
        }

        return dto;
    }

    public static List<QuestionReposeDto> toDtoList(List<Question> questions) {
        List<QuestionReposeDto> list = new ArrayList<>();
        for (Question q : questions) {
            list.add(toDto(q));
        }
        return list;
    }
}
