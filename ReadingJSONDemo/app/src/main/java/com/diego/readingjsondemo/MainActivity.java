package com.diego.readingjsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public TextView city;
    public TextView weatherText;
    public Button weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (TextView) findViewById(R.id.textCity);
        weatherText = (TextView) findViewById(R.id.weatherText);
        weatherButton = (Button) findViewById(R.id.weatherButton);

    }

    public String downloadUrl(){

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://api.openweathermap.org/data/2.5/weather?q="+city.getText().toString()+"&appid=60632f24b7806e2a5a3562e09bfa60f3").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject jsonInfo = null;
        try {
            jsonInfo = new JSONObject(result);
            String weatherInfo = jsonInfo.getString("weather");
            JSONArray arrayJSON = new JSONArray(weatherInfo);

            String main;
            String description;

            for(int i=0; i < arrayJSON.length(); i++){

                JSONObject jsonPart = arrayJSON.getJSONObject(i);
                main = jsonPart.getString("main");
                description = jsonPart.getString("description");

                weatherText.setText(main+": "+description);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }

    public void checkWeather(View view){

        downloadUrl();

    }

}
