package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookInfoPage extends AppCompatActivity {
    String bookTitle;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_page);
        databaseHelper = new DatabaseHelper(this);
        //TextView
        TextView title = findViewById(R.id.txtTitleRequestedBookFromDatabase);

        //sharedPrefernces for share title of selected book
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String s = "";
        bookTitle = sharedPreferences.getString("keySelectBook", s);
//       title.setText(bookTitle);

//        retrieve db
        Cursor c = databaseHelper.retreiveBooks();
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                if (c.getString(2).equals(bookTitle)) {
                    str.append("Book Name: " + c.getString(2));
                    str.append("\n");
                    str.append("Author: " + c.getString(3));
                    str.append("\n");
                    str.append("Publisher :" + c.getString(4));
                    str.append("\n");
                    str.append("Publisher: " + c.getString(5));
                    str.append("\n");
                    str.append("Owner: " + c.getString(8));
                    str.append("\n");

                    //putting the owner of the book in key value
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KeyOwner", c.getString(8));
                    editor.commit();


                }
            }


        } else {
            Toast.makeText(this, "No book Available", Toast.LENGTH_SHORT).show();
        }
        title.setText(str);

        Button btnRequestBook = findViewById(R.id.btnRequest);
        btnRequestBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookInfoPage.this, MessagePage.class));
            }
        });
    }

}