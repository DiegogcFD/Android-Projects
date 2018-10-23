package com.diego.timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Integer> numeros;
    public ArrayList<Integer> numerosAux;
    public ListView listNumbers;
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNumbers = (ListView) findViewById(R.id.listNumbers);

        numeros = new ArrayList<Integer>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);
        numeros.add(6);
        numeros.add(7);
        numeros.add(8);
        numeros.add(9);
        numeros.add(10);

        numerosAux = new ArrayList<>(numeros);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, numeros);
        listNumbers.setAdapter(adapter);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(20);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                for (int i = 0; i < numeros.size(); i++) {
                    numeros.set(i, progress * numerosAux.get(i));
                }
                adapter.notifyDataSetChanged();

                //minimum value 1
                if (seekBar.getProgress() < 1) {
                    seekBar.setProgress(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
