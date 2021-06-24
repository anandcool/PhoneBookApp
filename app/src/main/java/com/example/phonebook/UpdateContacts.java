package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UpdateContacts extends AppCompatActivity {

    ListView listviewupd;
    ArrayList arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contacts);
        listviewupd = findViewById(R.id.listviewupd);
        SQLiteDatabase db = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);
        Cursor cr = db.rawQuery("select * from users",null);
        if(cr.moveToFirst()){
            do{
                arrayList.add(cr.getString(cr.getColumnIndex(cr.getColumnName(1)))+" "+cr.getString(cr.getColumnIndex(cr.getColumnName(2))));
                Log.d("anything",""+cr.getString(cr.getColumnIndex(cr.getColumnName(1)))+" "+cr.getString(cr.getColumnIndex(cr.getColumnName(2))));
            }while (cr.moveToNext());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.listview2,R.id.textview,arrayList);
        listviewupd.setAdapter(adapter);
        listviewupd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fullname = (String) listviewupd.getItemAtPosition(i);
                Intent in = new Intent(UpdateContacts.this,FinalActivity.class);
                in.putExtra("fullname",fullname);
                startActivity(in);
                return false;
            }
        });
    }
}