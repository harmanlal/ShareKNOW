package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    DatabaseHelper db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        db = new DatabaseHelper(this);


        //register
        TextView txtNoAccount = findViewById(R.id.txtNoAccount);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtPassword = findViewById(R.id.txtPassword);


        txtNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, RegistrationPage.class));

            }
        });

        //login
        Button btnLoginPageUser = findViewById(R.id.btnLoginPageUser);
        btnLoginPageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtEmail.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "User Email cannot be blank.", Toast.LENGTH_SHORT).show();
                }
                else if (txtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "Password cannot be blank.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // validate user from database
                    String email = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();
                    c = db.checkUserRec(email,password);
                    if (c.getCount() > 0)    //if there are records to read
                    {

                        while (c.moveToNext()) {
                            int numOfUser = Integer.parseInt(c.getString(0));
                            if (numOfUser == 1) {
                                startActivity(new Intent(LoginPage.this, SplashScreen.class));
                            } else {
                                Toast.makeText(LoginPage.this, "Invalid email and password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        //admin login
        Button btnLoginAdmin = findViewById(R.id.btnLoginPageAdmin);
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginPage.this,AdminPanel.class));
            }
        });


    }


}
