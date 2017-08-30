package com.example.rikao20170713;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

//    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);

    }


    public void buttond(View view){

        getAsss("http://img.juhe.cn/cookbook/s/1/92_a377c03180cb6b26.jpg");


    }


    public void getAsss(String path){
        new AsyncTask<String, Void, Bitmap>(){

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if(bitmap!=null){
                    imageView.setImageBitmap(bitmap);


                }
                else{
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }


            }



            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int code = urlConnection.getResponseCode();
                    if(code==200){
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
