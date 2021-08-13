package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.widget.TextView;

import java.util.ArrayList;

public class timer extends AppCompatActivity {
    TextView exerciseName;
    TextView timeRemaining;
    MediaPlayer mediaPlayer;
    static int i = 0;

    ArrayList<String> currentExercise;
    public static final long START_TIME_IN_MILLIIS = 5000;
    public boolean mTimerRunning;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();
        currentExercise = intent.getStringArrayListExtra(MainActivity.array);
        exerciseName = findViewById(R.id.exerciseName);
        timeRemaining = findViewById(R.id.timeRemaining);
        mediaPlayer = MediaPlayer.create(this, R.raw.joinedaudio);


        Countdown();


    }

    public void Countdown() {
        new CountDownTimer(25000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {


                new

                        CountDownTimer(5000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                exerciseName.setText(currentExercise.get(i));
                                mTimeLeftInMillis = millisUntilFinished;
                                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
                                String timeformatted = String.format("%02d:%02d", minutes, seconds);


                                mediaPlayer.start();
                                timeRemaining.setText(timeformatted);

                            }

                            @Override
                            public void onFinish() {
                                i++;
                                mediaPlayer.pause();


                            }
                        }.

                        start();
            }  @Override
            public void onFinish() {
                exerciseName.setText("DONE");
                timeRemaining.setText("00:00");
                mediaPlayer.reset();
                i=0;

            }


        }.start();

    }

    @Override
    public void onBackPressed() {
        i=0;
        int pid=android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        super.onBackPressed();
    }

}
