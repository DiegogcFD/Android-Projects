package com.diego.braintestapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public TextView timeTextView;
    public TextView resultTextView;
    public TextView scoreTextView;
    public TextView question;
    public TextView answer1;
    public TextView answer2;
    public TextView answer3;
    public TextView answer4;
    public int correctAnswer;
    public int rightAnswers;
    public int numquestions;
    public boolean timerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questionTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);
        answer4 = (TextView) findViewById(R.id.answer4);

    }

    public void start(View view){

        rightAnswers = 0;
        numquestions = 1;
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);

        resultTextView.setText("");
        timerOn = true;

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        generateQuestion();


        new CountDownTimer(30000,1000){

            public void onTick(long millisUntilFinished) {
                timeTextView.setText("00: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerOn = false;
                timeTextView.setText("00:00");
                resultTextView.setText("SCORE: "+rightAnswers + "/" + numquestions);
                Button buttonTryAgain = (Button) findViewById(R.id.buttonTryAgain);
                buttonTryAgain.setVisibility(View.VISIBLE);
            }

        }.start();

    }

    public void generateQuestion(){

        if(timerOn) {
            scoreTextView.setText(rightAnswers + "/" + numquestions);

            Random rand = new Random();
            correctAnswer = rand.nextInt(3);
            int value1 = rand.nextInt(20);
            int value2 = rand.nextInt(20);
            int result = value1 + value2;

            question.setText(value1 + " + " + value2 + ":");
            answer1.setText(String.valueOf(rand.nextInt(50)));
            answer2.setText(String.valueOf(rand.nextInt(50)));
            answer3.setText(String.valueOf(rand.nextInt(50)));
            answer4.setText(String.valueOf(rand.nextInt(50)));

            if (correctAnswer == 0) {
                answer1.setText(String.valueOf(result));
            } else if (correctAnswer == 1) {
                answer2.setText(String.valueOf(result));
            } else if (correctAnswer == 2) {
                answer3.setText(String.valueOf(result));
            } else if (correctAnswer == 3) {
                answer4.setText(String.valueOf(result));
            }
        }
    }

    public void checkAnswer(View view){

        if(timerOn) {
            if (view.getTag().toString().equals(String.valueOf(correctAnswer))) {

                resultTextView.setText("Correct!");
                rightAnswers++;

            } else {

                resultTextView.setText("Wrong!");

            }

            numquestions++;
            generateQuestion();
        }

    }
}
