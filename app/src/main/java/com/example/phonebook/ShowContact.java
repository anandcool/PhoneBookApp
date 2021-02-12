package com.example.phonebook;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {

    ListView listView;
    final ArrayList<String> arraylist =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        listView = findViewById(R.id.listview);
        SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from users",null);
//        adapter = new ArrayAdapter<String >(this,R.layout.support_simple_spinner_dropdown_item,R.layout.support_simple_spinner_dropdown_item,listView);
        if (cursor.moveToFirst()) {
            do {
                Log.d("user23",""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))+","+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))+","+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))+","+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4))));
//                arraylist.add(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))+" "+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                arraylist.add(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))+" "+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
//
            } while (cursor.moveToNext());
//            listView.setAdapter(adapter);
            ArrayAdapter adapter = new ArrayAdapter(this,R.layout.listview2,R.id.textview,arraylist);
//
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(ShowContact.this, "Long Pressed!!"+listView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                    String name = (String) listView.getItemAtPosition(i);
                    Intent in = new Intent(getApplicationContext(),Details.class);
                    in.putExtra("name",name);
                    startActivity(in);
                    return false;
                }
            });
        }

    }
}