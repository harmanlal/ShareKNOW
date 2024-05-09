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
import android.widget.RadioButton;
import android.widget.Toast;

public class UpdatePage extends AppCompatActivity {

    //database helper class object
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        databaseHelper = new DatabaseHelper(this);

        //references
        EditText txtnewBookTitle = findViewById(R.id.txtAddBookTitle);
        EditText txtnewBookAuthor = findViewById(R.id.txtAddBookAuthor);
        EditText txtnewBookPublisher = findViewById(R.id.txtAddBookPublisher);
        EditText txtnewBookPublicationYear = findViewById(R.id.txtAddBookPublicationYear);

        RadioButton txtnewBookRent = findViewById(R.id.radioRentUpdate);
        RadioButton txtnewBookShare = findViewById(R.id.radioShareUpdate);
        RadioButton txtnewBookGive = findViewById(R.id.radioGiveUpdate);

        Button btnAddBook = findViewById(R.id.btnAddBook);
        Button btnAddBookCancel = findViewById(R.id.btnAddBookCancel);
        Button btnDelete = findViewById(R.id.btnAddBookDel);

        //to extract the information
        //to get the reference to shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //extract the information from shared preferences
        String s = "";
        String book = sharedPreferences.getString("key1",s);
        //Setting the text of book title
        txtnewBookTitle.setText(book);

        //retrieving book info from the database to be able to update (m)
        Cursor c = databaseHelper.retreiveBooks();
        StringBuilder str = new StringBuilder();
        if(c.getCount()>0)    //if there are records to read
        {
            //to move between the rows of cursor
            while(c.moveToNext())
            {
                //retrieve all info of the entered book from db and put into textview to update
                if(c.getString(2).equals(book)){
                    String Author= c.getString(3);
                    String Publisher= c.getString(4);
                    String Year = c.getString(5);

                    txtnewBookAuthor.setText(Author);
                    txtnewBookPublisher.setText(Publisher);
                    txtnewBookPublicationYear.setText(Year);
                }

            }
        }
        else
        {
            Toast.makeText(UpdatePage.this,"Nothing to read from database",Toast.LENGTH_LONG).show();
        }




        //When button is clicked, data is updated in the table books
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //variables
                String bookTitle, bookAuthor, bookPublisher, bookPubYear;
                String bookStatus = "";
                Boolean isUpdated;

                //getting the values
                bookTitle = txtnewBookTitle.getText().toString();
                bookAuthor = txtnewBookAuthor.getText().toString();
                bookPublisher = txtnewBookPublisher.getText().toString();
                bookPubYear = txtnewBookPublicationYear.getText().toString();

                //for status of the book that is changed
                if (txtnewBookGive.isChecked())
                    bookStatus = "Give Away";
                else if (txtnewBookRent.isChecked())
                    bookStatus = "Rent";
                else if (txtnewBookShare.isChecked())
                    bookStatus = "Share";

                isUpdated = databaseHelper.updateBookRec(bookTitle, bookAuthor, bookPublisher, bookPubYear, bookStatus);
                if (isUpdated) {
                    startActivity(new Intent(UpdatePage.this, ConfirmationUpdateBook.class));
                    Toast.makeText(UpdatePage.this, "Record Updated in the Book Table", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdatePage.this, "Record not Updated", Toast.LENGTH_LONG).show();
                }


            }
        });

        //deleting book by the user
        btnDelete.setOnClickListener(new View.OnClickListener() {
            String delBook;
            Boolean isDeleted;
            @Override
            public void onClick(View view) {
                delBook = txtnewBookTitle.getText().toString();

                isDeleted=databaseHelper.delBook(delBook);
                if(isDeleted)
                    Toast.makeText(UpdatePage.this,"Record Deleted From Books",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdatePage.this,"Record not deleted",Toast.LENGTH_LONG).show();

            }
        });

        //else go to add/update book
        btnAddBookCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdatePage.this,AddUpdateChoicePage.class));
            }
        });


    }
}

