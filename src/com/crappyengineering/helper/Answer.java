package com.crappyengineering.helper;

public class Answer{

    Object answer;

    public Answer(Object answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer = { " + answer + " }";
    }
}
