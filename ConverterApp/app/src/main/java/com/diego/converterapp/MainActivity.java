package com.diego.converterapp;

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

    public void converterClick(View v){

        EditText valor = (EditText)findViewById(R.id.idEditText);

        Double valorConvertido = Double.parseDouble(valor.getText().toString())*2.8;

        Toast toast = Toast.makeText(getApplicationContext(),"CA$"+String.format("%.2f", valorConvertido),Toast.LENGTH_SHORT);
        toast.show();

    }
}
