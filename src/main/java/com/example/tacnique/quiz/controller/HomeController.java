package com.example.tacnique.quiz.controller;

import com.example.tacnique.quiz.entity.Quiz;
import com.example.tacnique.quiz.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final QuizService quizService;

    public HomeController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Quiz> quizzes = quizService.findAll();
        model.addAttribute("quizzes", quizzes);
        return "public/home";
    }
}
