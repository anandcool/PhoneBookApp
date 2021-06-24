package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnadd,btnshow,btnupdate,btndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.add);
        btnshow = findViewById(R.id.show);
        btndelete = findViewById(R.id.delete);
        btnupdate = findViewById(R.id.update);

        //Click Listener or you can click on the button and go to the following activity
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,AddContact.class);
                startActivity(in);
            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,ShowContact.class);
                startActivity(in);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,DeleteContacts.class);
                startActivity(in);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,UpdateContacts.class);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //For Inflating the Menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list, menu);
        return true;
    }
}