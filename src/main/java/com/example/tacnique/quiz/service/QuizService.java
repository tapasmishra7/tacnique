package com.example.tacnique.quiz.service;


import com.example.tacnique.quiz.entity.Quiz;
import com.example.tacnique.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }
}