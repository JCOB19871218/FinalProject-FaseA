package com.example.demo.repository;

import com.example.demo.entity.example.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleRepository extends JpaRepository<Example,Long> {
    List<Example> findByCourseIdAndProfessorId(Long courseId, Long professorId);
}
