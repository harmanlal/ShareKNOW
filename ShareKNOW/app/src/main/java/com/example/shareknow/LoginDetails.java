package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);

        //textView
        TextView txtUsername = findViewById(R.id.txtUserNameInfo);
        TextView txtpassword = findViewById(R.id.txtPassInfo);

        //shared preferences
        //to get the reference to shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //extract the information from shared preferences
        String u = "";
        String p = "";
        String uName = sharedPreferences.getString("keyUser", u);
        String pass = sharedPreferences.getString("keyPass", p);

        //setting up text field of textview
        txtUsername.setText("Username: "+uName);
        txtpassword.setText("Password: "+pass);

        //btn
        Button btn = findViewById(R.id.btnBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginDetails.this,LoginPage.class));
            }
        });


    }

}