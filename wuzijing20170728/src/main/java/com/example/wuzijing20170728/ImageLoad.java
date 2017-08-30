package com.example.wuzijing20170728;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 姓名：吴子敬
 * 时间：20170728
 * 作用：ImageLoad做一个接口
 */

public class ImageLoad extends AsyncTask<String, Void, Bitmap> {

    public interface ImageCallBack {

        void imagecall(Bitmap bitmap);

    }

    private ImageCallBack imageCallBack;

    public ImageLoad(ImageCallBack imageCallBack) {
        this.imageCallBack = imageCallBack;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageCallBack.imagecall(bitmap);

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
