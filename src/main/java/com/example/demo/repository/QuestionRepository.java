package com.example.demo.repository;

import com.example.demo.entity.example.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByProfessorId(Long professorId);

}
