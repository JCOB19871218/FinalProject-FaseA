package com.example.demo.entity.example;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Example {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;


    @Column(nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private User professor;

    @OneToMany(mappedBy = "example", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ExampleQuestion> questions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ExampleStatus status;
}
