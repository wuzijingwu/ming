package com.example.imageloadfromnewwork;

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
        imageView = (ImageView) findViewById(R.id.ivloadimage);


    }

    public void button(View view) {

        loadimage("http://img.juhe.cn/cookbook/s/1/92_a377c03180cb6b26.jpg");

    }

    public void loadimage(String imagepath) {
        new AsyncTask<String, Void, Bitmap>() {

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                //UI在在此运行

                if(bitmap!=null){
                    imageView.setImageBitmap(bitmap);



                }

                else{

                    imageView.setImageResource(R.mipmap.ic_launcher);

                }







            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected Bitmap doInBackground(String... params) {

                //得到网络图片地址
                try {
                    String path = params[0];
                    URL url = new URL(path);
                  HttpURLConnection  urlConnection= (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int code = urlConnection.getResponseCode();
                    if (code == 200) {
                        InputStream is = urlConnection.getInputStream();
                        Bitmap stream = BitmapFactory.decodeStream(is);
                        return stream;
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(imagepath);


    }


}
