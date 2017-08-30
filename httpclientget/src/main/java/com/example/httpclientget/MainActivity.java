package com.example.httpclientget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.httpclientget.utils.StreamTools;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buton(View view) throws Exception {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://apis.juhe.cn/cook/query.php?menu=秘制红烧肉&key=ff00d7339861c7fd7d5b54b16b76422a");
                    HttpResponse response = httpClient.execute(httpGet);

                    int code = response.getStatusLine().getStatusCode();
                    if (code == 200) {
                        InputStream is = response.getEntity().getContent();
                        String netWork = StreamTools.readFromNetWork(is);
                        System.out.println("sdfgfdsasdfgfdssdfgfds" + netWork);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }

}
