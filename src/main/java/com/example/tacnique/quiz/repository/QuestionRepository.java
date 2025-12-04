package com.example.tacnique.quiz.repository;

import com.example.tacnique.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}