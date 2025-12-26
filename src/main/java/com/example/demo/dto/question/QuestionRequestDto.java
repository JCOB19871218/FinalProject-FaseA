package com.example.demo.dto.question;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDto {
    private String title;
    private String questionText;
    private List<OptionRequestDto> options;
}
