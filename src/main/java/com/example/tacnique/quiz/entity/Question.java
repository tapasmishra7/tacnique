package com.example.tacnique.quiz.entity;

import jakarta.persistence.*;
import jakarta.persistence.Transient;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    /**
     * For MCQ: JSON array of options, e.g. ["A","B","C","D"]
     * For TRUE_FALSE/TEXT: can be null
     */
    @Lob
    private String optionsJson;

    /**
     * For MCQ: store the correct option index or key, e.g. "0" or "A"
     * For TRUE_FALSE: "TRUE" or "FALSE"
     * For TEXT: currently unused (could store model answer in future)
     */
    private String correctAnswer;

    // getters & setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }

    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public QuestionType getType() { return type; }

    public void setType(QuestionType type) { this.type = type; }

    public String getOptionsJson() { return optionsJson; }

    public void setOptionsJson(String optionsJson) { this.optionsJson = optionsJson; }

    public String getCorrectAnswer() { return correctAnswer; }

    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    @Transient
    public List<String> getOptionsList() {
        if (optionsJson == null || optionsJson.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.asList(optionsJson.split("\\|\\|"));
    }
}
