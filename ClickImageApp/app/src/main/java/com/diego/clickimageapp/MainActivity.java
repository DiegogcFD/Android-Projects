package com.diego.clickimageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button b1;
    ImageView iw;
    boolean control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.btnChange);
        b1.setOnClickListener(this);

        iw = (ImageView) findViewById(R.id.icon);

        control = true;
    }

    @Override
    public void onClick(View v) {

        if (v == b1)
        {
            if(control) {
                iw.setImageResource(R.drawable.proto);
                control = false;
            } else {
                iw.setImageResource(R.drawable.main);
                control = true;
            }
        }

    }


}
