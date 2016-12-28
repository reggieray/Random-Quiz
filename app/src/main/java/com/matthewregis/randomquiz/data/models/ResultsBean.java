package com.matthewregis.randomquiz.data.models;

import java.util.List;

/**
 * Created by reg on 28/11/2016.
 */

public class ResultsBean {
    /**
     * category : Entertainment: Film
     * type : multiple
     * difficulty : easy
     * question : What breed of dog was &#039;Marley&#039; in the film &#039;Marley &amp; Me&#039;?
     * correct_answer : Labrador Retriever
     * incorrect_answers : ["Golden Retriever","Dalmatian","Shiba Inu"]
     */

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(List<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
