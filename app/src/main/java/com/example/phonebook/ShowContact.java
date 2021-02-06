package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowContact extends AppCompatActivity {

    ListView listView;
    final ArrayAdapter<String> adapter = null;
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
                Log.d("user",""+cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
                adapter.add(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

            } while (cursor.moveToNext());
//            listView.setAdapter(adapter);
        }

    }
}