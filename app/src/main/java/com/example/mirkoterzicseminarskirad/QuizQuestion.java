package com.example.mirkoterzicseminarskirad;

import java.io.Serializable;

public  class QuizQuestion implements Serializable {
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    private String question;
    private String[] options;
    private String correctAnswer;

    public  QuizQuestion(String question, String[] options, String correctAnswer ){
        this.question=question;
        this.options=options;
        this.correctAnswer=correctAnswer;
    }



}
