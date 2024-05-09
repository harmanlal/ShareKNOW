package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Reference
        EditText txtBTitle = findViewById(R.id.txtAddBookTitle);
        EditText txtBAuthor = findViewById(R.id.txtAddBookAuthor);
        EditText txtBPublisher = findViewById(R.id.txtAddBookPublisher);
        EditText txtBPubYear = findViewById(R.id.txtAddBookPublicationYear);
        EditText txtBCategory = findViewById(R.id.txtBookCategory);
        TextView emailUser = findViewById(R.id.txtEmailUser);

        Button addBook = findViewById(R.id.btnAddBook);
        Button cancelAddBook = findViewById(R.id.btnAddBookCancel);

        RadioButton bookstatusGive = findViewById(R.id.radioGiveUpdate);
        RadioButton bookStatusRent = findViewById(R.id.radioRentUpdate);
        RadioButton bookStatusShare = findViewById(R.id.radioShareUpdate);

        //receiving email key and put to top of page
        String Em = "";
        userEmail = sharedPreferences.getString("keyUser", Em);
        emailUser.setText(userEmail);



        //if add button is clicked
        addBook.setOnClickListener(new View.OnClickListener() {
            //variables
            String bTitle,bAuthor,bPublisher,bPubYear,bStatus,bCategory;
            Boolean isInserted;

            @Override
            public void onClick(View view) {
                bTitle = txtBTitle.getText().toString();
                bAuthor = txtBAuthor.getText().toString();
                bPublisher = txtBPublisher.getText().toString();
                bPubYear = txtBPubYear.getText().toString();
                bCategory = txtBCategory.getText().toString();

                //for book status
                if (bookstatusGive.isChecked())
                    bStatus = "Give Away";
                else if (bookStatusRent.isChecked())
                    bStatus = "Rent";
                else if (bookStatusShare.isChecked())
                    bStatus = "Share";

                //call the method
                isInserted = databaseHelper.addBookRec(bTitle,bAuthor,bPublisher,bPubYear,bStatus,bCategory,userEmail);

                //if records are inserted , show the toast message
                if(isInserted)
                    Toast.makeText(AddBook.this, "Data is inserted into Book records", Toast.LENGTH_LONG).show();
                else
                     Toast.makeText(AddBook.this, "An error occurred", Toast.LENGTH_LONG).show();
            }
    });

        //else back to add/update page
        cancelAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddBook.this,AddUpdateChoicePage.class));
            }
        });
    }
}