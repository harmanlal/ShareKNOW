package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //receiving email key and put to top of page
        String Em = "";
       String userEmail = sharedPreferences.getString("keyUser", Em);

       //text
        TextView splashText = findViewById(R.id.txtUserSplash);
        splashText.setText("Welcome "+userEmail);

        //creating the timer task object
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {   //refering to activity which will be displayed next
                startActivity(new Intent(SplashScreen.this,UserPage.class));
                finish();
            }
        };


        //creating the timer object
        Timer time = new Timer();
        time.schedule(timerTask,5000);  //delay of 5 seconds

    }

    }
