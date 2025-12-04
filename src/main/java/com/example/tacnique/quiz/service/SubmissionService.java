package com.example.tacnique.quiz.service;

import com.example.tacnique.quiz.entity.QuizSubmission;
import com.example.tacnique.quiz.repository.QuizSubmissionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    private final QuizSubmissionRepository quizSubmissionRepository;

    public SubmissionService(QuizSubmissionRepository quizSubmissionRepository) {
        this.quizSubmissionRepository = quizSubmissionRepository;
    }

    public QuizSubmission saveSubmission(QuizSubmission submission) {
        return quizSubmissionRepository.save(submission);
    }

    // grading logic will be added later
}