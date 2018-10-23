package com.diego.hiddendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button hideButton = (Button) findViewById(R.id.hideButton);
        //Button showButton = (Button) findViewById(R.id.showButton);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void show(View v){


        textView.setVisibility(View.VISIBLE);

    }

    public void hide(View v){

        textView.setVisibility(View.INVISIBLE);

    }

}
