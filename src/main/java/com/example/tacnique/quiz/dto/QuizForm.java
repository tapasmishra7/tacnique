package com.example.tacnique.quiz.dto;

import java.util.ArrayList;
import java.util.List;

public class QuizForm {

    private String title;
    private List<QuestionForm> questions = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionForm> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionForm questionForm) {
        this.questions.add(questionForm);
    }
}
