package com.taflah.thirdprojectcreatingaquizapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizMainActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewCorrectAnswerCount;
    private TextView textViewIncorrectAnswerCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RelativeLayout relativeLayout;
    private TextView finalResultTextView;
    private MediaPlayer correctMediaPlayer, incorrectMediaPlayer;


    private Question question = new Question();
    private ArrayList<Integer> myAnswers;
    private int locationOfCorrectAnswer;
    private int incorrectAnswer;
    private Random random = new Random();
    private int correctAnswerCount = 1;
    private int incorrectAnswerCount = 1;
    private int numberOfQuestion = 1 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        findMyViewsByIs();
        getQuestion();
    }

    public void nextQuestion(View view) {

        if (numberOfQuestion < question.getMaxTries()){
            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                checkAnswer();
                getQuestion();
                numberOfQuestion++;
            } else {
                Toast.makeText(QuizMainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
            }
        } else {
            relativeLayout.setVisibility(View.INVISIBLE);
            if(correctAnswerCount == numberOfQuestion) {
                finalResultTextView.setText("Your Score is " + correctAnswerCount + "/" + numberOfQuestion + "\n\n\n You are an amazing");
                finalResultTextView.setVisibility(View.VISIBLE);
            } else if(correctAnswerCount >= 5){
                finalResultTextView.setText("Your Score is " + correctAnswerCount + "/" + numberOfQuestion + "\n\n\n   good for you");
                finalResultTextView.setVisibility(View.VISIBLE);
            } else{
                finalResultTextView.setText("Your Score is "+ correctAnswerCount + "/" + numberOfQuestion + "\n\n\n       you failed" );
                finalResultTextView.setVisibility(View.VISIBLE);}

        }
    }

    private void findMyViewsByIs(){
        textViewQuestion = findViewById(R.id.questionTextView);
        textViewCorrectAnswerCount = findViewById(R.id.correctAnswersTextView);
        textViewIncorrectAnswerCount = findViewById(R.id.incorrectAnswersTextView);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        relativeLayout = findViewById(R.id.relativeLayout);
        finalResultTextView = findViewById(R.id.finalResultTextView);
    }

    private void getQuestion(){
        myAnswers = new ArrayList<Integer>();
        question.setQuestion();
        rbGroup.clearCheck();

        textViewQuestion.setText(Integer.toString(question.getLeftAdder()) + " + " + Integer.toString(question.getRightAdder()));

        locationOfCorrectAnswer = random.nextInt(3);

        for (int i = 0; i<3 ; i++){
            if( i == locationOfCorrectAnswer){
                myAnswers.add(question.getCorrectAnswer());
            } else {
                incorrectAnswer = random.nextInt(100) + 40;
                while (incorrectAnswer == question.getCorrectAnswer()){
                    incorrectAnswer = random.nextInt(100) + 40;
                }
                myAnswers.add(incorrectAnswer);
            }

        }
        rb1.setText(Integer.toString(myAnswers.get(0)));
        rb2.setText(Integer.toString(myAnswers.get(1)));
        rb3.setText(Integer.toString(myAnswers.get(2)));
    }

    private void checkAnswer() {
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected);

        if (answerNr == locationOfCorrectAnswer) {
            correctAnswerCount++;
            textViewCorrectAnswerCount.setText("correct: " + (correctAnswerCount -1));
            correctMediaPlayer = MediaPlayer.create(this,R.raw.correct);
            correctMediaPlayer.start();
            Toast.makeText(getApplicationContext(), "Correct!",Toast.LENGTH_SHORT).show();
        } else {
            incorrectAnswerCount++;
            textViewIncorrectAnswerCount.setText("incorrect: " + (incorrectAnswerCount - 1));
            incorrectMediaPlayer = MediaPlayer.create(this,R.raw.incorrect);
            incorrectMediaPlayer.start();
            Toast.makeText(getApplicationContext(), "Incorrect!",Toast.LENGTH_SHORT).show();
        }
        getQuestion();
    }



    }





