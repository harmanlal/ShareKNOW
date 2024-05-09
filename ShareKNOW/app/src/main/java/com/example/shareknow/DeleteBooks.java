package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteBooks extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_books);

        databaseHelper = new DatabaseHelper(this);

        //references
        EditText txtBookIdToDel = findViewById(R.id.textBookIdToDel);
        Button btn = findViewById(R.id.btnDelBook);

        btn.setOnClickListener(new View.OnClickListener() {
            String name = "";
            Boolean isDeleted;
            @Override
            public void onClick(View view) {
                //getting the data from the admin
                name = txtBookIdToDel.getText().toString();
                //calling the method
                isDeleted=databaseHelper.delBook(name);
                if(isDeleted)
                    Toast.makeText(DeleteBooks.this,"Record Deleted From Books",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeleteBooks.this,"Record not deleted",Toast.LENGTH_LONG).show();
            }
        });


    }
}