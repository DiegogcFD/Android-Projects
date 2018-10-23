package com.diego.eggtimerapp;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public TextView timerTextView;
    public SeekBar seekBar;
    public CountDownTimer countDownTimer;
    public long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

//        option using handler
//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes = progress/60;
                int seconds = progress - minutes*60;

                String secondString = Integer.toString(seconds);

                if (secondString == "0") {

                    secondString = "00";

                }

                timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
                timeLeft = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start(View v){
        startTimer(timeLeft);
    }

    private void startTimer(final long milisecods) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(milisecods*1000, 500) {

            @Override
            public void onTick(long leftTimeInMilliseconds) {

                int barVal = (int) leftTimeInMilliseconds / 1000;
                seekBar.setProgress(barVal);
                timerTextView.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds),
                        TimeUnit.MILLISECONDS.toSeconds(leftTimeInMilliseconds) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds))
                ));
                Log.i("tempo que falta",String.valueOf(leftTimeInMilliseconds/1000));

            }

            @Override
            public void onFinish() {

            }

        }.start();

    }
}
