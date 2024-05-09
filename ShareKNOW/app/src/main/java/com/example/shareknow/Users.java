package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Users extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        databaseHelper = new DatabaseHelper(this);

        //Text view
        TextView txtUsersData = findViewById(R.id.textViewUsers);

                //cursor object
                Cursor c = databaseHelper.viewUsers();

                //retrieving the data
                StringBuilder str = new StringBuilder();
                if (c.getCount() > 0)    //if there are records to read
                {
                    //to move between the rows of cursor
                    while (c.moveToNext()) {
                        str.append("UserName:" +c.getString(2) + " " +c.getString(3));
                        str.append("\n\n");
                    }
                    txtUsersData.setText(str);
                }
                else
                {
                    Toast.makeText(Users.this, "Nothing to read from database", Toast.LENGTH_LONG).show();
                }

}}


