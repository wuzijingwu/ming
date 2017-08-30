package com.example.wuzijing20170721rikao;

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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.images);
        try {
//        ("http://apis.juhe.cn/cook/query?key=15023923d1fc4d0c8f5c538f49d0baab&menu=" + URLEncoder.encode("秘制红烧肉", "utf-8").getBytes())
            String path = "http://img.juhe.cn/cookbook/s/1/45_f5489af5d12b4930.jpg";
            GetDates(path, image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });


    }

    private void GetDates(String path, final ImageView image) {

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    image.setImageBitmap(bitmap);

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
