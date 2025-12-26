package com.example.demo.entity.example;

import com.example.demo.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

@Inheritance(strategy = InheritanceType.JOINED)
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String questionText;

    @ManyToOne
    private User professor;

}
