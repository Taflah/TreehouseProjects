package com.taflah.thirdprojectcreatingaquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_start);
    }

    private void startMyQuiz(){
        Intent intent = new Intent(StartScreenActivity.this, QuizMainActivity.class);
        startActivity(intent);
    }

    public void startQuiz(View view){
        startMyQuiz();
    }
}
