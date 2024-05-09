package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessagePage extends AppCompatActivity {
    String msg, sender, receiver;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_page);
        //database object
        databaseHelper = new DatabaseHelper(this);

        //TextView
        TextView txtSender= findViewById(R.id.txtSnder);
        TextView txtReceiver = findViewById(R.id.txtRceiver);

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        //receiving user(sender) by the key
        String s="";
        sender = sharedPreference.getString("keyUser", s);
        //receiving owner by the key
        String o="";
        receiver = sharedPreference.getString("KeyOwner", o);

        //set the sender and receiver to textview
        txtSender.setText(sender);
        txtReceiver.setText(receiver);




        EditText txtMsg = findViewById(R.id.txtMessage);
        Button message = findViewById(R.id.btnSendMsg);
        message.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View view) {
                msg = txtMsg.getText().toString();
                
                //add message rec to db
                isInserted = databaseHelper.addMessageRec(sender, receiver, msg);
                if(isInserted){
                    Toast.makeText(MessagePage.this, "Message sent!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MessagePage.this, UserPage.class));
                }else{
                    Toast.makeText(MessagePage.this, "Message not sent!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}