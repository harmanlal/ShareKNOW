package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class RegistrationPage extends AppCompatActivity {
    String email, fn, gn, dob, address;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        //Refrences
        EditText txtEmail = findViewById(R.id.txtEmailRegister);
        EditText txtGName = findViewById(R.id.txtGnameRegister);
        EditText txtFName = findViewById(R.id.txtFnameRegister);
        EditText txtAddress = findViewById(R.id.txtAddressRegister);
        EditText txtDateOfBirth = findViewById(R.id.txtDateRegister);

       RadioButton fiction = findViewById(R.id.rbFiction);
       RadioButton sFiction = findViewById(R.id.rbScienceFictrion);
       RadioButton science = findViewById(R.id.rbScience);
       RadioButton novel = findViewById(R.id.rbNovel);

        // adding datepicker
        txtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                txtDateOfBirth.setText(
                                        (monthOfYear + 1) + "/" +dayOfMonth + "/" +  year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //when register button is pressed
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            //variables
            String readingInterest = "";
            Boolean isInserted;

            @Override
            public void onClick(View view) {

                email = txtEmail.getText().toString();
                fn = txtFName.getText().toString();
                gn = txtGName.getText().toString();
                dob = txtDateOfBirth.getText().toString();
                address = txtAddress.getText().toString();

                //for reading interest
                if (fiction.isChecked())
                    readingInterest = "Fiction ";
                else if (sFiction.isChecked())
                    readingInterest = "Science-fiction ";
                else if (science.isChecked())
                    readingInterest = "Science ";
                else if (novel.isChecked())
                    readingInterest = "Novel ";

                //call the method
                isInserted = databaseHelper.addRec(email, gn, fn, dob, address, readingInterest);

                //if records are inserted , show the toast message
                if (isInserted) {
                    Toast.makeText(RegistrationPage.this, "Data is inserted into our records", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MainActivity.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                    Toast.makeText(RegistrationPage.this, "An error occurred", Toast.LENGTH_LONG).show();
                }
            }


        });


        //for login page
        Button btnLoginNow = findViewById(R.id.btnLoginNow);
        btnLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationPage.this,LoginPage.class));
            }
        });

        //For LogInfo Insertion
        Button btnLoginfo = findViewById(R.id.btnLogInfo);
        btnLoginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text,dob;
                String username="";
                String password="";
                String sChar = "@";
                String pass = "";
                String front = "";

                username = txtEmail.getText().toString();
                dob = txtDateOfBirth.getText().toString();

                pass = dob.substring(0,2);
                front = username.substring(0,2);

                password = front+sChar+pass;

                //saving details to database
                Boolean isInserted;
                isInserted= databaseHelper.addLogInfo(username,password);

                //using shared preferences to pass the value to next activity
                //to edit the shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //to save the data into key value pairs
                editor.putString("keyUser", username);
                editor.putString("keyPass",password);
                //in order to save the changes
                editor.commit();
                //start the login activity
                startActivity(new Intent(RegistrationPage.this,LoginDetails.class));

            }
        });

    }

}