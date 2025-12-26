package com.example.demo.entity.example;

import com.example.demo.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Example example;

    @Column(length = 2000)
    private String descriptiveAnswer;



    @ManyToOne
    private Option selectedOption;

    private Integer score;

}
