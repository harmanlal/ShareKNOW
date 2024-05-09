package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReadingTracker extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_tracker);

        databaseHelper = new DatabaseHelper(this);

        //receiving email key and put it into username field automatically
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String Em = "";
        String userName = sharedPreferences.getString("keyUser", Em);

        //TextBox
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtBook = findViewById(R.id.txtBookRT);
        EditText txtAuthor = findViewById(R.id.txtAuthorRT);
        EditText txtPublisher = findViewById(R.id.txtPublisherRT);

        //Setting the text field
        txtUserName.setText(userName);

        Button btnEnter = findViewById(R.id.buttonEnter);
        //when button is pressed, table is created and details are saved
        btnEnter.setOnClickListener(new View.OnClickListener() {

            String username,book,author,publisher;
            Boolean isInserted;

            @Override
            public void onClick(View view) {

                username = txtUserName.getText().toString();
                book = txtBook.getText().toString();
                author = txtAuthor.getText().toString();
                publisher = txtPublisher.getText().toString();

                isInserted = databaseHelper.readingTrackerRec(username,book,author,publisher);
                if(isInserted)
                    Toast.makeText(ReadingTracker.this, "Information Collected", Toast.LENGTH_LONG).show();
             else
                    Toast.makeText(ReadingTracker.this, "Information Not Collected", Toast.LENGTH_LONG).show();
            }
        });

    }

    //cancel button action
    public void cancelBtn(View view){
        startActivity(new Intent(ReadingTracker.this, UserPage.class));
    }
}