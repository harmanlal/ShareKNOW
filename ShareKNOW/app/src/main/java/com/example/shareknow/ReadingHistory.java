package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReadingHistory extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_history);

        databaseHelper = new DatabaseHelper(this);

        TextView txtRData = findViewById(R.id.txtReadingHisData);

        //cursor object
        Cursor c = databaseHelper.viewReadingHistory();

        //retrieving the data
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0)    //if there are records to read
        {
            //to move between the rows of cursor
            while (c.moveToNext()) {
                str.append("UserName:" +c.getString(1));
                str.append("Book Read: " +c.getString(2));
                str.append("Book Publisher: " +c.getString(3));
                str.append("Publication Year: " +c.getString(4));
                str.append("\n\n");
            }
            txtRData.setText(str);
        }
        else
        {
            Toast.makeText(ReadingHistory.this, "Nothing to read from database", Toast.LENGTH_LONG).show();
        }

    }
}