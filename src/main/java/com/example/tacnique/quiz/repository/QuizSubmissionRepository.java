package com.example.tacnique.quiz.repository;

import com.example.tacnique.quiz.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
}