package com.example.tacnique.quiz.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuizSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quiz quiz;

    private LocalDateTime submittedAt;

    private Integer score;

    private Integer maxScore;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionAnswer> answers = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.submittedAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }

    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }

    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public Integer getMaxScore() { return maxScore; }

    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }

    public List<SubmissionAnswer> getAnswers() { return answers; }

    public void setAnswers(List<SubmissionAnswer> answers) { this.answers = answers; }

    public void addAnswer(SubmissionAnswer answer) {
        answers.add(answer);
        answer.setSubmission(this);
    }
}