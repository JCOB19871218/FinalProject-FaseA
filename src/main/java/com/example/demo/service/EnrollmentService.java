package com.example.demo.service;

public interface EnrollmentService {
    void addProfessor(Long courseId, Long professorId);
    void addStudent(Long courseId, Long studentId);
}
