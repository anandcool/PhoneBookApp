package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent in = getIntent();
        phone = findViewById(R.id.phone);
        String name = in.getStringExtra("name");
        String[] fullname = name.split(" ");
        Toast.makeText(this, "name" + name, Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = openOrCreateDatabase("phonebook", MODE_PRIVATE, null);//create database connection
        Cursor cursor = db.rawQuery("select * from users where fname = '" + fullname[0] + "'", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("user67", "" + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))) + "," + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))) + "," + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))) + "," + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
                phone.setText(""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
            } while (cursor.moveToNext());
        }
    }
}