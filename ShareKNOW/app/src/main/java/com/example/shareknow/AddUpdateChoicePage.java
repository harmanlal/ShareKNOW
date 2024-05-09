package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddUpdateChoicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_choice_page);

        //references to the button
        TextView txt1 = findViewById(R.id.txtAddBook);
        TextView txt2 = findViewById(R.id.txtUpdateBook);

        //txt1 click
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddUpdateChoicePage.this,AddBook.class));
            }
        });

        //txt2 click
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddUpdateChoicePage.this,SelectToUpdate.class));
            }
        });

    }

}