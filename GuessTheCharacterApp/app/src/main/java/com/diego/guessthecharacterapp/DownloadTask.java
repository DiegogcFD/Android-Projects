package com.diego.guessthecharacterapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {


        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection = null;

        try{
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }

        return result.toString();
    }

}