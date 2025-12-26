package com.example.demo.repository;

import com.example.demo.entity.example.ExampleQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleQuestionRepository extends JpaRepository<ExampleQuestion,Long> {
    boolean existsByExampleIdAndQuestionId(Long exampleId, Long questionId);

    List<ExampleQuestion> findByExampleId(Long exampleId);
}
