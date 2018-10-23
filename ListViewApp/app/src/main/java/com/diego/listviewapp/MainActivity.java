package com.diego.listviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView)findViewById(R.id.myListView);

        final ArrayList<String> names = new ArrayList<String>();
        names.add("Diego1");
        names.add("Diego2");
        names.add("Diego3");
        names.add("Diego4");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);

        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),"Hello "+names.get(position)+"!", Toast.LENGTH_SHORT).show();

                makeToast(names.get(position));
                Log.i("nome", ((ListView)parent).getAdapter().getItem(position).toString());

            }
        });

    }

    public void makeToast( String name){

        Toast.makeText(this,"Hello "+name+"!", Toast.LENGTH_SHORT).show();

    }
}
