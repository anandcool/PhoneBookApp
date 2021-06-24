package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {
    EditText fname,lname,phone,email;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        update = findViewById(R.id.update);
        Intent in = getIntent();
        String fullname = in.getStringExtra("fullname");
        String[] name = fullname.split(" ");
        SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from users where fname='"+name[0]+"'",null);
        if (cursor.moveToFirst()) {
            Log.d("detailsofupdateuser",""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))+"--"+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))+"---"+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
            fname.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
            lname.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
            phone.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
            email.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname1,lname1,email1,phone1;
                fname1 = fname.getText().toString();
                lname1 = lname.getText().toString();
                email1 = email.getText().toString();
                phone1 = phone.getText().toString();
                SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);//create database connection
                db.execSQL("UPDATE `users` SET `fname`='"+fname1+"',`lname`='"+lname1+"',`email`='"+email1+"' where `phone`='"+phone1+"'");//update command
                db.close();//connection close
                Toast.makeText(FinalActivity.this, "Update Contact", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),MainActivity.class);//Move one screen to another screen
                startActivity(in);
                finish();
            }
        });
    }
}