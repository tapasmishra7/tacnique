package com.example.tacnique.quiz.controller;

import com.example.tacnique.quiz.entity.Question;
import com.example.tacnique.quiz.entity.QuestionType;
import com.example.tacnique.quiz.entity.Quiz;
import com.example.tacnique.quiz.entity.QuizSubmission;
import com.example.tacnique.quiz.entity.SubmissionAnswer;
import com.example.tacnique.quiz.service.QuizService;
import com.example.tacnique.quiz.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PublicQuizController {

    private final QuizService quizService;
    private final SubmissionService submissionService;

    public PublicQuizController(QuizService quizService,
                                SubmissionService submissionService) {
        this.quizService = quizService;
        this.submissionService = submissionService;
    }

    @GetMapping("/quiz/{id}")
    public String showQuiz(@PathVariable Long id, Model model) {
        Optional<Quiz> quizOpt = quizService.findById(id);
        if (quizOpt.isEmpty()) {
            // very simple 404-style handling
            model.addAttribute("message", "Quiz not found");
            return "public/quiz-not-found";
        }

        model.addAttribute("quiz", quizOpt.get());
        return "public/take-quiz";
    }

    @PostMapping("/quiz/{id}/submit")
    public String submitQuiz(@PathVariable Long id,
                             HttpServletRequest request,
                             Model model) {
        Optional<Quiz> quizOpt = quizService.findById(id);
        if (quizOpt.isEmpty()) {
            model.addAttribute("message", "Quiz not found");
            return "public/quiz-not-found";
        }

        Quiz quiz = quizOpt.get();

        QuizSubmission submission = new QuizSubmission();
        submission.setQuiz(quiz);

        int score = 0;
        int maxScore = 0;

        List<SubmissionAnswer> answers = new ArrayList<>();

        for (Question question : quiz.getQuestions()) {
            String paramName = "q_" + question.getId();
            String userAnswer = request.getParameter(paramName);

            SubmissionAnswer sa = new SubmissionAnswer();
            sa.setSubmission(submission);
            sa.setQuestion(question);
            sa.setAnswer(userAnswer != null ? userAnswer : "");

            if (question.getType() == QuestionType.MCQ || question.getType() == QuestionType.TRUE_FALSE) {
                maxScore++;
                boolean isCorrect = userAnswer != null &&
                        question.getCorrectAnswer() != null &&
                        userAnswer.trim().equalsIgnoreCase(question.getCorrectAnswer().trim());
                sa.setCorrect(isCorrect);
                if (isCorrect) {
                    score++;
                }
            } else {
                // TEXT: not auto-graded
                sa.setCorrect(null);
            }

            answers.add(sa);
        }

        submission.setAnswers(answers);
        submission.setScore(score);
        submission.setMaxScore(maxScore);

        QuizSubmission saved = submissionService.saveSubmission(submission);

        model.addAttribute("quiz", quiz);
        model.addAttribute("submission", saved);

        return "public/quiz-result";
    }
}