package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Books extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        databaseHelper = new DatabaseHelper(this);

        //Text view
        TextView txtBooksData = findViewById(R.id.textBooksData);

        //cursor object
        Cursor c = databaseHelper.retreiveBooks();

        //retrieving the data
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0)    //if there are records to read
        {
            //to move between the rows of cursor
            while (c.moveToNext()) {
                str.append("Book Name:" +c.getString(2));
                str.append("Availability: " +c.getString(7));
                str.append("\n\n");
            }
            txtBooksData.setText(str);
        }
        else
        {
            Toast.makeText(Books.this, "Nothing to read from database", Toast.LENGTH_LONG).show();
        }
    }
}