package com.diego.menuanddatastoragedemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        language = (TextView) findViewById(R.id.language);

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);

        String chosenLanguage = sharedPreferences.getString("language","");

        if(chosenLanguage.equals("")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Which language do you want to use?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "English selected!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("language", "English").apply();
                            language.setText(sharedPreferences.getString("language", ""));
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Spanish selected!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("language", "Spanish").apply();
                            language.setText(sharedPreferences.getString("language", ""));
                        }
                    })
                    .show();

        } else {
            language.setText(sharedPreferences.getString("language", ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.diego.menuanddatastoragedemo", Context.MODE_PRIVATE);
        switch (item.getItemId()){

            case R.id.english:
                Toast.makeText(MainActivity.this, "English selected!", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("language", "English").apply();
                language.setText(sharedPreferences.getString("language",""));
                return true;
            case R.id.spanish:
                Toast.makeText(MainActivity.this, "Spanish selected!", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("language", "Spanish").apply();
                language.setText(sharedPreferences.getString("language",""));
                return true;
             default:
                 return true;

        }
    }
}
