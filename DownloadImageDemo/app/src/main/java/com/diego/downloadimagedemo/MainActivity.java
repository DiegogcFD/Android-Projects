package com.diego.downloadimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    public ImageView imageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public void downloadImage(View view){

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute("https://www.smashbros.com/wiiu-3ds/images/character/rockman/main.png").get();
            imageView.setImageBitmap(myImage);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
