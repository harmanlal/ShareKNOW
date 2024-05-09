package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class AvailableBooks extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_books);

        databaseHelper = new DatabaseHelper(this);

        //TEXTVIEW
        TextView txtData = findViewById(R.id.txtAvailableBooksData);
        TextView txtLoc = findViewById(R.id.txtLocation);

        //EditText
        EditText selectBookBox = findViewById(R.id.boxSelectBook);


        //to extract the information
        //to get the reference to shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //extract the information from shared preferences
        String s = "";
        String code = sharedPreferences.getString("key2", s);
        txtLoc.setText("Location Entered: "+code);

        //cursor object
        Cursor c;
        c = databaseHelper.retrieveBooksbyLocation(code);
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0)    //if there are records to read
        {
            //to move between the rows of cursor
            while (c.moveToNext()) {
                if (c.getString(14).equals(code)) {
                    str.append("Book Name: "+c.getString(2));
                    str.append("\n");
                    //setting the data
                    txtData.setText(str);
                }
                else {
                    Toast.makeText(AvailableBooks.this, "Nothing matches with database", Toast.LENGTH_LONG).show();
                }


            }}
                //button event
                Button selectBook = findViewById(R.id.btnSelectBook);
                selectBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        book = selectBookBox.getText().toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("keySelectBook", book);
                        editor.commit();
                        startActivity(new Intent(AvailableBooks.this, BookInfoPage.class));
                    }
                });
            }


        }

