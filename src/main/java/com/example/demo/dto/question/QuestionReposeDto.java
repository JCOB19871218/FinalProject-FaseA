package com.example.demo.dto.question;

import lombok.*;

import java.util.List;
@Getter
@Setter
public class QuestionReposeDto {
    private Long id;
    private String title;
    private String questionText;
    private List<OptionRequestDto> options;
    private Integer defaultScore;

}
