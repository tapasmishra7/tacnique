package com.example.tacnique.quiz.dto;

import com.example.tacnique.quiz.entity.QuestionType;

public class QuestionForm {

    private String text;
    private QuestionType type;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    /**
     * For MCQ: "0","1","2","3" (index of correct option).
     * For TRUE_FALSE: "TRUE" or "FALSE".
     * For TEXT: ignored.
     */
    private String correctAnswer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
