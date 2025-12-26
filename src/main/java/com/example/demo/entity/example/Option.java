package com.example.demo.entity.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private boolean correct;

    @ManyToOne
    private MultipleChoiceQuestion question;
}
