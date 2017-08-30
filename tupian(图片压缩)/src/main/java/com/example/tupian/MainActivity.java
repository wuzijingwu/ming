package com.example.tupian;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loadimage("http://i3.s2.dpfile.com/2010-12-20/6201691_b.jpg(249x249)/thumb.jpg");

            }
        });


    }

    public void Loadimage(String path) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    image.setImageBitmap(bitmap);

                }else{
                    image.setImageResource(R.mipmap.ic_launcher);

                }

            }

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        options.inSampleSize=2;
                        int Width = options.outWidth;
                        int Height = options.outHeight;

                        int inSampleSize=1;
                         if(Width>100||Height>100){

                             int i = Width / 2;
                             int i1 = Height / 2;
                             while((i/inSampleSize)>=100 && (i1/inSampleSize)>=100){
                                 inSampleSize*=2;
                             }


                         }

                        options.inSampleSize=inSampleSize;

                        options.inJustDecodeBounds = false;


                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                        return  bitmap;


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);


    }


}
