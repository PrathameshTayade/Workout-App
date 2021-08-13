package com.example.homeworkout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public  static  final  String array= "array";
    MediaPlayer mediaPlayer;

    ArrayList<String> TodaysExercise;

    ArrayList<String> Ex = new ArrayList<>();
    ArrayList<String> today = new ArrayList<>();
    int a[] = new int[5];

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getex() {

        int index;
        Random random = new Random();

        Ex.add("pushups");
        Ex.add("crunches");
        Ex.add("seatups");
        Ex.add("squats");
        Ex.add("weightlifting");
        Ex.add("pullups");
        Ex.add("skipping");
        Set<Integer> set = new LinkedHashSet<Integer>();

        while (set.size() < 5) {
            index = random.nextInt(Ex.size());
            set.add(index);

        }


        int a[] = set.stream().mapToInt(Integer::intValue).toArray();


        for (int i = 0; i < 5; i++) {
            today.add(Ex.get(a[i]));
        }
        return today;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(this,R.raw.joinedaudio);

        ListView exercises = (ListView) findViewById(R.id.listview);


        TodaysExercise = getex();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TodaysExercise);
        exercises.setAdapter(arrayAdapter);


    }

//    public void Play(View v) {
//        new CountDownTimer(5000, 5000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//               mediaPlayer.start();
//
//            }
//
//            @Override
//            public void onFinish() {
//                mediaPlayer.pause();
//
//            }
//        }.start();
//    }


    public void Play(View v){

        Intent intent = new Intent(this,timer.class);
        intent.putExtra(array,TodaysExercise);
        startActivity(intent);




    }

}
