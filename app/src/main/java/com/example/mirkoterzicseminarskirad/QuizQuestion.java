package com.example.mirkoterzicseminarskirad;

import java.io.Serializable;

public  class QuizQuestion implements Serializable {
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    private String question;
    private String[] options;
    private String correct_answer;

    public  QuizQuestion(String question, String[] options, String correct_answer ){
        this.question=question;
        this.options=options;
        this.correct_answer =correct_answer;
    }



}
