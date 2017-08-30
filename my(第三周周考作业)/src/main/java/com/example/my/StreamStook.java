package com.example.my;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dell on 2017/7/18.
 */

public class StreamStook {
    public static String read(InputStream is) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = is.read(bytes)) != -1) {

            byteArrayOutputStream.write(bytes, 0, len);


        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toString();
    }


}





//
//
//
//
//
//
//
//
//
//
//    private void Loadimages(String path, final ImageView imageitem2) {
//        new AsyncTask<String, Void, Bitmap>() {
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                if (bitmap != null) {
//                    imageitem2.setImageBitmap(bitmap);
//
//                }
//
//
//            }
//
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                try {
//                    String path = params[0];
//                    URL url = new URL(path);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setRequestMethod("GET");
//                    urlConnection.setConnectTimeout(2000);
//                    urlConnection.setReadTimeout(2000);
//                    int code = urlConnection.getResponseCode();
//                    if (code == 200) {
//                        InputStream inputStream = urlConnection.getInputStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        return bitmap;
//
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//                return null;
//            }
//        }.execute(path);
//
//
//    }



