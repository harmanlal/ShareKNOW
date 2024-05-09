package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        //incoming requests
        Button btnViewRequests = findViewById(R.id.btnMessageReq);
        btnViewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,IncomingRequest.class));
            }
        });

        //log out
        Button btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,MainActivity.class));
            }
        });

        //view users
        Button btnViewUsers = findViewById(R.id.btnView);
        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,Users.class));
            }
        });

        //view books
        Button btnViewBooks = findViewById(R.id.btnViewBooks);
        btnViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,Books.class));
            }
        });

        //Delete Users
        Button btnDeleteUsers = findViewById(R.id.btnDelUser);
        btnDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,DeleteUsers.class));
            }
        });

        //Delete Books
        Button btnDeleteBooks = findViewById(R.id.button);
        btnDeleteBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,DeleteBooks.class));
            }
        });

        //reading history
        Button btnReadingHistory = findViewById(R.id.btnReadingHistory);
        btnReadingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,ReadingHistory.class));
            }
        });


    }
}