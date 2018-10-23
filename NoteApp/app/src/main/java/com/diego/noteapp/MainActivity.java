package com.diego.noteapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> notes;
    public static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.listView);
        notes.add("Example 1");
        notes.add("Example 2");

        arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });

//        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
//
//        String chosenLanguage = sharedPreferences.getString("language","");
//
//        if(chosenLanguage.equals("")) {
//
//            new AlertDialog.Builder(this)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setTitle("Which language do you want to use?")
//                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(MainActivity.this, "English selected!", Toast.LENGTH_SHORT).show();
//                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
//                            sharedPreferences.edit().putString("language", "English").apply();
//                            language.setText(sharedPreferences.getString("language", ""));
//                        }
//                    })
//                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(MainActivity.this, "Spanish selected!", Toast.LENGTH_SHORT).show();
//                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
//                            sharedPreferences.edit().putString("language", "Spanish").apply();
//                            language.setText(sharedPreferences.getString("language", ""));
//                        }
//                    })
//                    .show();
//
//        } else {
//            language.setText(sharedPreferences.getString("language", ""));
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
        if (item.getItemId() == R.id.newNote) {

        }

        return super.onOptionsItemSelected(item);
    }
}
