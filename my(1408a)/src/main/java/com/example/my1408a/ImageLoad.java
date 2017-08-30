package com.example.my1408a;

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
        void imagcall(Bitmap bitmap);

    }

    private Imagecallback imagecallback;

    public ImageLoad(Imagecallback imagecallback) {

        this.imagecallback = imagecallback;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imagecallback.imagcall(bitmap);


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
            if (code == 200) {
                InputStream is = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
