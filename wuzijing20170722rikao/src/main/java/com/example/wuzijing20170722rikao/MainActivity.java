package com.example.wuzijing20170722rikao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.images);
        String path = "http://i1.s1.dpfile.com/pc/f59ce7b879eea202f36692aa9ead9dac(249x249)/thumb.jpg";
        getDates(path, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Secondactivity.class);
                startActivity(intent);


            }
        });

    }

    public void getDates(String path, final ImageView imageView) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }


            }

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(2000);
                    urlConnection.setReadTimeout(2000);
                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        return bitmap;


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);


    }
}
