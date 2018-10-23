package com.robpercival.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickButton(View view){

        EditText textUser = (EditText) findViewById(R.id.idTextUser);
        EditText textPassword = (EditText) findViewById(R.id.idTextPassword);

        //Log.i("Info","User: "+textUser.getText().toString()+" - Password: "+textPassword.getText().toString());

        Toast.makeText(MainActivity.this, textUser.getText().toString(), Toast.LENGTH_LONG).show();

    }
}
