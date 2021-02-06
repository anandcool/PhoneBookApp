package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

EditText fname,lname,email,phone;
Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnsubmit = findViewById(R.id.submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);
                db.execSQL("create table if not exists users(id integer primary key autoincrement, fname varchar,lname varchar,phone varchar,email varchar)");
                db.execSQL("insert into users(fname,lname,phone,email) VALUES ('"+fname.getText().toString()+"','"+lname.getText().toString()+"','"+phone.getText().toString()+"','"+email.getText().toString()+"')");
                db.close();
                Toast.makeText(AddContact.this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}