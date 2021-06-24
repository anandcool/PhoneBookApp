package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowAllDetails extends AppCompatActivity {
TextView tphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_details);
        tphone = findViewById(R.id.tphone);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};//Permission
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        Intent in = getIntent(); // get the Intent value
        String fullname = in.getStringExtra("fullname1"); // Ajay Devgn
        String[] fullname1 = fullname.split(" ");
        SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null); // create database connection
        Cursor cursor = db.rawQuery("select * from users where fname='"+fullname1[0]+"'",null);
        if (cursor.moveToFirst()) {
            Log.d("detailsofuser",""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))+"--"+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))+"---"+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
            tphone.setText(""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
        }
        tphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+tphone.getText()));//change the number
                startActivity(callIntent);
            }
        });
    }
}