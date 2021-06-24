package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        fname = findViewById(R.id.fname); // Map the java button with xml
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnsubmit = findViewById(R.id.submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);//create the connection of database
                db.execSQL("create table if not exists users(id integer primary key autoincrement, fname varchar,lname varchar,phone varchar,email varchar)"); //create table cmd
                db.execSQL("insert into users(fname,lname,phone,email) VALUES ('"+fname.getText().toString()+"','"+lname.getText().toString()+"','"+phone.getText().toString()+"','"+email.getText().toString()+"')");// insertion command
                db.close();//close database connection
                fname.setText("");//set blank value of EditText
                lname.setText("");
                email.setText("");
                phone.setText("");
                Toast.makeText(AddContact.this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();//For showing value on the phone for some time.
                Intent in = new Intent(AddContact.this,ShowContact.class); //Move one screen to another screen
                startActivity(in);
                finish();
            }
        });
    }
}