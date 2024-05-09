package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserPage extends AppCompatActivity {
    String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        TextView txtUserEmail = findViewById(R.id.txtUserEmail);
        Button btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserPage.this,UpdateUserProfile.class));
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //receiving email key and put to top of page(user email)
        String Em = "";
        userEmail = sharedPreferences.getString("keyUser", Em);
        txtUserEmail.setText(userEmail);

    }

    public void addUpdateEnter(View view) {
        startActivity(new Intent(UserPage.this, AddUpdateChoicePage.class));
    }

    public void BorrowEnter(View view) {
        startActivity(new Intent(UserPage.this, BorrowPage.class));
    }

    public void rTrackerEnter(View view) {
        startActivity(new Intent(UserPage.this, ReadingTracker.class));
    }

    public void viewResponses(View view)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String m = "",response = "";
        response = sharedPreferences.getString("key1",m);
        //setting up toast message to show the reponse
        if(response.length()>0) {
            Toast.makeText(UserPage.this, response, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(UserPage.this,"No Recent Responses", Toast.LENGTH_LONG).show();

        }
    }

}