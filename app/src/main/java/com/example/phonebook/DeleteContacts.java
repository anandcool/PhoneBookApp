package com.example.phonebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class DeleteContacts extends AppCompatActivity {

    ListView listviewdel;
    ArrayList arrayList = new ArrayList();
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contacts);
        listviewdel = findViewById(R.id.listviewdel);
        SQLiteDatabase database = openOrCreateDatabase("phonebook",MODE_PRIVATE,null);//create database connection
        Cursor cr = database.rawQuery("select * from users",null);//select command of database
        if(cr.moveToFirst()){ // move to  the first record of the table from the database
            do{
                arrayList.add(cr.getString(cr.getColumnIndex(cr.getColumnName(1)))+" "+cr.getString(cr.getColumnIndex(cr.getColumnName(2)))); //adding column to the arraylist
            }while (cr.moveToNext());

            ArrayAdapter adapter = new ArrayAdapter(this,R.layout.listview2,R.id.textview,arrayList);
            listviewdel.setAdapter(adapter);
           listviewdel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                   String fullname = (String) listviewdel.getItemAtPosition(i);
                   AlertDialog.Builder builder = new AlertDialog.Builder(DeleteContacts.this); //Alert Box Creation

                   builder.setTitle("Are you sure you want to delete this contact "+fullname);
                   builder.setCancelable(true);
                   builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           String [] name = fullname.split(" "); //Get the name from the previous screen
                    database.execSQL("DELETE FROM `users` WHERE `fname`='"+name[0]+"'"); // Delete command of database
                    database.close();//connection close
                   Intent intent = new Intent(DeleteContacts.this,MainActivity.class); //Move one screen to another acitiviy
                   startActivity(intent);
                       }
                   });
                   builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           alert.cancel();
                       }
                   });
                   alert= builder.create();
                   alert.show();//Alert Show
                   return false;
               }
           });
        }
    }
}