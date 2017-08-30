package com.example.bannerdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell on 2017/7/12.
 */

public class Load extends AsyncTask<String, Void, Bitmap> {

    public interface ImagecallbackLister {


        void imagecallback(Bitmap bitmap);


    }

    private ImagecallbackLister imagecallbackLister;

    public Load(ImagecallbackLister imagecallbackLister) {
        this.imagecallbackLister = imagecallbackLister;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imagecallbackLister.imagecallback(bitmap);


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


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
