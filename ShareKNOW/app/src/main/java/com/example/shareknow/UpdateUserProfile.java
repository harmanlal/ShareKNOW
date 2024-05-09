package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserProfile extends AppCompatActivity {
    DatabaseHelper db;
    String loggedinUserEmail = "";
    String name;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        EditText PersonName = findViewById(R.id.PersonName);
        EditText Email = findViewById(R.id.Email);
        EditText HomeAddress = findViewById(R.id.HomeAddress);
        Button btnUdateProfile = findViewById(R.id.btnUdateProfile);

        db = new DatabaseHelper(this);
        Cursor c;
        c = db.getUserRec();
        btnUdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = PersonName.getText().toString();
                loggedinUserEmail = Email.getText().toString();
                address = HomeAddress.getText().toString();
                if(db.updateUserInfoRec(name, loggedinUserEmail, address)) {
                    Toast.makeText(UpdateUserProfile.this, "Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateUserProfile.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (c.getCount() > 0)    //if there are records to read
        {

                while (c.moveToNext()) {
                    loggedinUserEmail = c.getString(1);
                    Cursor c2 = db.getUserDataRec(loggedinUserEmail);

                    if (c2.getCount() > 0)    //if there are records to read
                    {

                            while (c2.moveToNext()) {
                                name = c2.getString(2);
                                address = c2.getString(5);
                                PersonName.setHint(name);
                                PersonName.setText(name);
                                Email.setHint(loggedinUserEmail);
                                Email.setText(loggedinUserEmail);
                                HomeAddress.setHint(address);
                                HomeAddress.setText(address);
                            }

                    }
            }

        }
    }
}