package com.example.wuzijing1506a20170724;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell on 2017/7/24.
 */

public class ImageLoad extends AsyncTask<String, Void, Bitmap> {

    public interface Imagecallbacll {
        void imagesss(Bitmap bitmap);

    }

    private Imagecallbacll imagecallbacll;

    public ImageLoad(Imagecallbacll imagecallbacll) {

        this.imagecallbacll = imagecallbacll;

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
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
