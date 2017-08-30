package com.example.dier2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell on 2017/7/16.
 */

public class LoadImage extends AsyncTask<String, Void, Bitmap> {

    public interface ImageCallbackLister{

        void imagecall(Bitmap bitmap);
    }

    private ImageCallbackLister imageCallbackLister;
    public  LoadImage(ImageCallbackLister imageCallbackLister){

        this.imageCallbackLister=imageCallbackLister;


    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageCallbackLister.imagecall(bitmap);


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
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
