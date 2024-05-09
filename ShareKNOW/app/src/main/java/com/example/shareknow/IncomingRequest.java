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

public class IncomingRequest extends AppCompatActivity {
    String username;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_request);

        db = new DatabaseHelper(this);

        //TextView Reference
        TextView txtData = findViewById(R.id.txtMessageReq);

        //Cursor object
        Cursor c;

        //Calling the method
        c = db.viewMessageRequests();
        //retrieving the data
        StringBuilder str = new StringBuilder();
        if (c.getCount() > 0)    //if there are records to read
        {
            //to move between the rows of cursor
            while (c.moveToNext()) {
                str.append("Sender Name:" + c.getString(1));
                //for deletion purpose
                username = c.getString(1);
                //nextline
                str.append("\n");
                str.append("Message: " + c.getString(3));
                str.append("\n\n");
            }

            //setting the text
            txtData.setText(str);
        } else {
            Toast.makeText(IncomingRequest.this, "Nothing to read from database", Toast.LENGTH_LONG).show();
        }

        //shared preferences for message
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Button events
        Button btnResponse = findViewById(R.id.btnRespond);
        Button btnDelReq = findViewById(R.id.btndelReq);

        //BtnResponse will send direct message to user via shared preferences
        btnResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting up automated message
                String message = "REQUEST ACCEPTED FOR THE BOOK BURROW. THANKS FOR USING ShareKNOW";
                //to edit the shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //to save the data into key value pairs
                editor.putString("key1", message);
                //in order to save the changes
                editor.commit();
                Toast.makeText(IncomingRequest.this, "Message sent to user", Toast.LENGTH_LONG).show();

            }
        });

        //btnDelReq will delete the request from the table once it is completed
        btnDelReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isDeleted;

                //calling the method
                isDeleted = db.delReq(username);
                if (isDeleted)
                    Toast.makeText(IncomingRequest.this, "Request Removed Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(IncomingRequest.this, "Request not Removed", Toast.LENGTH_LONG).show();
            }
        });

        //Updating Availabilty
        Button btnUpdate = findViewById(R.id.btnUpBook);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //retrieving the selected book from the shared preferences
                //from Available Books.java
                //field from selected book textbox
                String book = "",b="";
                Boolean isUpdated;
                book = sharedPreferences.getString("keySelectBook",b);

                //updating the status
                isUpdated = db.updateBookAvail(book);

                if(isUpdated)
                {
                    Toast.makeText(IncomingRequest.this,"Book Availability Changed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(IncomingRequest.this,"Book Availability Not Changed",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    }