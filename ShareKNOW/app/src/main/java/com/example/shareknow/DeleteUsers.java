package com.example.shareknow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUsers extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_users);

        databaseHelper = new DatabaseHelper(this);

        //references
        EditText txtUserIdToDel = findViewById(R.id.txtDeleteUserId);
        Button btn = findViewById(R.id.btnDeleteUser);

        btn.setOnClickListener(new View.OnClickListener() {
            String name;
            Boolean isDeleted;
            @Override
            public void onClick(View view) {
                //getting the data from the admin
                name= txtUserIdToDel.getText().toString();
                //calling the method
                isDeleted=databaseHelper.delUser(name);
                if(isDeleted)
                    Toast.makeText(DeleteUsers.this,"Record Deleted From Users",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeleteUsers.this,"Record not deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
}