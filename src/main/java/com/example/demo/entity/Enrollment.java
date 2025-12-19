package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

    private LocalDate enrollmentDate;



}
