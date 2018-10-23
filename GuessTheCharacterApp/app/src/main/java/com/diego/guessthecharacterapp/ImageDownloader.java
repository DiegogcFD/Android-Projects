package com.diego.guessthecharacterapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    protected Bitmap doInBackground(String... urls) {

        URL url;
        HttpURLConnection connection = null;

        try{
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

            return myBitmap;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}