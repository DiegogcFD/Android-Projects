package com.diego.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //TODO Fazer toda a parte de vitoria e empate

    boolean firstPlayer = true;

    /* 0 means o
       1 means x
       2 means empty */
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPositions [][] ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean winner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked (View v) {

        ImageView clicked = (ImageView) v;
        int tag = Integer.valueOf(clicked.getTag().toString());

        if (gameState[tag] == 2) {
            if (firstPlayer) {
                clicked.setImageResource(R.drawable.tic_x);
                gameState[tag] = 1;
                firstPlayer = false;
            } else {
                clicked.setImageResource(R.drawable.tic_o);
                gameState[tag] = 0;
                firstPlayer = true;
            }
        }

        checkWinner();
        if(winner){



        }
    }

    public void checkWinner(){

        for(int[] winningPositions : winningPositions){

            if(gameState[winningPositions[0]] == gameState[winningPositions[1]]
               && gameState[winningPositions[0]] == gameState[winningPositions[2]]
                    && gameState[winningPositions[2]] != 2){

                winner = true;

            }

        }

    }

}
