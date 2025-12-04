package com.example.tacnique.quiz.controller;

import com.example.tacnique.quiz.dto.QuestionForm;
import com.example.tacnique.quiz.dto.QuizForm;
import com.example.tacnique.quiz.entity.Question;
import com.example.tacnique.quiz.entity.QuestionType;
import com.example.tacnique.quiz.entity.Quiz;
import com.example.tacnique.quiz.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminQuizController {

    private final QuizService quizService;

    public AdminQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/admin/quizzes/new")
    public String showCreateQuizForm(Model model) {
        QuizForm quizForm = new QuizForm();
        // initialize with 3 empty questions
        for (int i = 0; i < 3; i++) {
            QuestionForm qf = new QuestionForm();
            qf.setType(QuestionType.MCQ); // default
            quizForm.addQuestion(qf);
        }
        model.addAttribute("quizForm", quizForm);
        model.addAttribute("questionTypes", QuestionType.values());
        return "admin/new-quiz";
    }

    @PostMapping("/admin/quizzes")
    public String createQuiz(@ModelAttribute("quizForm") QuizForm quizForm,
                             RedirectAttributes redirectAttributes) {

        Quiz quiz = new Quiz();
        quiz.setTitle(quizForm.getTitle());

        List<Question> questions = new ArrayList<>();
        if (quizForm.getQuestions() != null) {
            quizForm.getQuestions().forEach(qf -> {
                if (qf.getText() == null || qf.getText().isBlank()) {
                    // skip completely empty question rows
                    return;
                }

                Question question = new Question();
                question.setQuiz(quiz);
                question.setText(qf.getText());
                question.setType(qf.getType() != null ? qf.getType() : QuestionType.MCQ);

                if (question.getType() == QuestionType.MCQ) {
                    // Join non-null options with a separator
                    List<String> options = new ArrayList<>();
                    if (qf.getOptionA() != null) options.add(qf.getOptionA());
                    if (qf.getOptionB() != null) options.add(qf.getOptionB());
                    if (qf.getOptionC() != null) options.add(qf.getOptionC());
                    if (qf.getOptionD() != null) options.add(qf.getOptionD());
                    String joined = String.join("||", options);
                    question.setOptionsJson(joined);
                    question.setCorrectAnswer(qf.getCorrectAnswer());
                } else if (question.getType() == QuestionType.TRUE_FALSE) {
                    question.setOptionsJson(null); // not needed
                    // expected "TRUE" or "FALSE"
                    question.setCorrectAnswer(qf.getCorrectAnswer());
                } else { // TEXT
                    question.setOptionsJson(null);
                    question.setCorrectAnswer(null); // not auto-graded
                }

                questions.add(question);
            });
        }

        quiz.setQuestions(questions);

        quizService.save(quiz);

        redirectAttributes.addFlashAttribute(
                "message",
                "Quiz created successfully with ID: " + quiz.getId()
                        + " (public link: /quiz/" + quiz.getId() + ")"
        );
        return "redirect:/admin/quizzes/new";
    }
}