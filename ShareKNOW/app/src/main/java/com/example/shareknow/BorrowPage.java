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
import android.widget.Toast;

public class BorrowPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_page);
        Button btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Find();
            }
        });
    }

    //button click event will take the control to next activity
    public void Find() {
        //references
        EditText txtPostal = findViewById(R.id.txtViewPostal);
        String code;

        //getting the postal code
        code = txtPostal.getText().toString();

        //converting to upper case if in case it is in lowercase
//        code = code.toUpperCase();

        //using shared preferences to pass the code value to next activity
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //to edit the shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //to save the data into key value pairs
        editor.putString("key2", code);
        //in order to save the changes
        editor.commit();
        startActivity(new Intent(BorrowPage.this, AvailableBooks.class));





    }
}




