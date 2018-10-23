package com.diego.guessthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int guessNumber = 0;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rand = new Random();
        guessNumber = rand.nextInt(50);
    }

    public void guessButtonClick(View v){



        EditText idEditText = (EditText)findViewById(R.id.idEditText);
        String numero = idEditText.getText().toString();
        Integer numeroInt = Integer.parseInt(numero);

        if(numeroInt > guessNumber){
            Toast.makeText(getApplicationContext(),"lower!",Toast.LENGTH_SHORT).show();
        } else if (numeroInt < guessNumber){
            Toast.makeText(getApplicationContext(),"higher!",Toast.LENGTH_SHORT).show();;
        } else {
            Toast.makeText(getApplicationContext(),"You got it! The number is: "+guessNumber+"! Try again!",Toast.LENGTH_SHORT).show();;
            guessNumber = rand.nextInt(50);
        }
    }
}
