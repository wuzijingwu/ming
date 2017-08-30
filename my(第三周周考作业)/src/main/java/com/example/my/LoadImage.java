package com.example.my;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell on 2017/7/19.
 */

public class LoadImage extends AsyncTask<String, Void, Bitmap> {

    public interface Imagrcallbac {
        void imagecall(Bitmap bitmap);
    }

    private Imagrcallbac imagrcallbac;

    public LoadImage(Imagrcallbac imagrcallbac) {

        this.imagrcallbac = imagrcallbac;


    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imagrcallbac.imagecall(bitmap);


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
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
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
