package com.example.tacnique.quiz.entity;

import jakarta.persistence.*;

@Entity
public class SubmissionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private QuizSubmission submission;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;

    /**
     * Raw answer as string:
     * - MCQ: selected option key/index
     * - TRUE_FALSE: "TRUE" / "FALSE"
     * - TEXT: user-entered text
     */
    @Lob
    private String answer;

    /**
     * null for TEXT questions (not auto-graded)
     */
    private Boolean correct;

    // getters & setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public QuizSubmission getSubmission() { return submission; }

    public void setSubmission(QuizSubmission submission) { this.submission = submission; }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public Boolean getCorrect() { return correct; }

    public void setCorrect(Boolean correct) { this.correct = correct; }
}