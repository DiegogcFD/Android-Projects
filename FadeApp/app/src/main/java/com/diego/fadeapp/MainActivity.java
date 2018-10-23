package com.diego.fadeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void swapImage(View view){

        ImageView megaman = (ImageView) findViewById(R.id.megaman);
        megaman.animate().xBy(2000).setDuration(2000);

        //ImageView protoman = (ImageView) findViewById(R.id.protoman);
        //protoman.animate().alpha(1f).setDuration(2000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
