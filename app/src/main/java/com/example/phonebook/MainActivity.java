package com.example.phonebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnadd,btnshow,btnupdate,btndelete;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.add);
        btnshow = findViewById(R.id.show);
        btndelete = findViewById(R.id.delete);
        btnupdate = findViewById(R.id.update);

//        alert = new AlertDialog(this);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.showinfo:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //Alert Box Creation

                builder.setTitle("Know More About the creator of App");
                builder.setMessage("Nikola Jovanoski");
                builder.setCancelable(true);
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                final View view = layoutInflater.inflate(R.layout.sample,null);
                builder.setView(view);
                alert= builder.create();
                alert.show();//Alert Show

        }


        return false;
    }
}