//////////////////////////////SELECT BOOK/////////////////////////////////////
package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SelectToUpdate extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_to_update);

        databaseHelper = new DatabaseHelper(this);

        //References
        EditText txtBookSelected = findViewById(R.id.txtBookNameSelected);

        //Retrieving the data entered by user
        String book = txtBookSelected.getText().toString();

        //TextView
        TextView txtBooks = findViewById(R.id.txtBookData);

        //data retrieval
        Cursor c = databaseHelper.retreiveBooks();
        StringBuilder str = new StringBuilder();
        if(c.getCount()>0)    //if there are records to read
        {
            //to move between the rows of cursor
            while(c.moveToNext())
            {
                str.append("Book Name: " + c.getString(2));  //Books Names are at 2 position
                str.append("\n\n");
            }
        }
        else
        {
            Toast.makeText(SelectToUpdate.this,"Nothing to read from database",Toast.LENGTH_LONG).show();
        }
        txtBooks.setText(str);
    }


    //button event
    public void update(View view){

        //we will use shared preferences in order to pass the name of the book to updatePage
        EditText bookToUpdate = findViewById(R.id.txtBookNameSelected);
        String bookSelected = bookToUpdate.getText().toString();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //to edit the shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //to save the data into key value pairs
        editor.putString("key1", bookSelected);
        //in order to save the changes
        editor.commit();
        startActivity(new Intent(SelectToUpdate.this, UpdatePage.class));
    }
}