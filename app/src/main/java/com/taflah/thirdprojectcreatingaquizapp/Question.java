package com.taflah.thirdprojectcreatingaquizapp;


import java.util.Random;

public class Question {
    private static final int MAX_TRIES = 10;
    Random random = new Random();
    private int leftAdder;
    private int rightAdder;
    private int correctAnswer;


    public void setQuestion(){
        leftAdder = random.nextInt(50) + 20;
        rightAdder = random.nextInt(50) + 20;
        correctAnswer = leftAdder + rightAdder;
    }



    public static int getMaxTries() {
        return MAX_TRIES;
    }


    public int getLeftAdder() {
        return leftAdder;
    }

    public int getRightAdder() {
        return rightAdder;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
