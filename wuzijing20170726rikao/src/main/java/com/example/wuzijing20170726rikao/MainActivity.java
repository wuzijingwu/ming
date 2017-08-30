package com.example.wuzijing20170726rikao;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);

        getDates();


    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void getDates(String path) {

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    Gson gson = new Gson();
                    MenuInfo menuInfo = gson.fromJson(s, MenuInfo.class);
                    List<MenuInfo.ResultBean> result = menuInfo.getResult();



                }



            }

            @Override
            protected String doInBackground(String... params) {

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
                        String json = StreamTook.read(is);
                        return json;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);


    }
}
