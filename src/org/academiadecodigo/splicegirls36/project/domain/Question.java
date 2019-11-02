package org.academiadecodigo.splicegirls36.project.domain;

public class Question {

    private final String text;
    private final String correctAnswer;

    public Question (String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;

    }


    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
