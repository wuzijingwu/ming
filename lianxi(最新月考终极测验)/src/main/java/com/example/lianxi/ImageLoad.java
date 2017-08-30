package com.example.lianxi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell on 2017/7/26.
 */

public class ImageLoad extends AsyncTask<String, Void, Bitmap> {

    public interface Imagecallback {

        void imagecall(Bitmap bitmap);

    }

    private Imagecallback imagecallback;

    public ImageLoad(Imagecallback imagecallback) {
        this.imagecallback = imagecallback;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imagecallback.imagecall(bitmap);


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
