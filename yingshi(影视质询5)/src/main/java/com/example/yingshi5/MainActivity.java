package com.example.yingshi5;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.yingshi5.adapter.MyAdapter;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultUserTokenHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText moviename;
    private Button find;
    private ListView listview;
    private ExpandableListView ex_act;
    private ExpandableListView video_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviename = (EditText) findViewById(R.id.moviename);
        find = (Button) findViewById(R.id.find);
        listview = (ListView) findViewById(R.id.listview);
        ex_act = (ExpandableListView) findViewById(R.id.ex_act);
        video_rec = (ExpandableListView) findViewById(R.id.video_rec);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = moviename.getText().toString();
                    String path = "http://op.juhe.cn/onebox/movie/video?key=557d71a8ddefaef26187298e837c9f25&q="
                            + URLEncoder.encode(name, "utf-8");
                    MyAsynctask myAsynctask = new MyAsynctask();
                    myAsynctask.execute(path);


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    public class MyAsynctask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                dialog.dismiss();
                Gson gson = new Gson();
                Movie movie = gson.fromJson(s, Movie.class);
                listview.setAdapter(new MyAdapter(MainActivity.this,movie));
                ArrayList<Movie.Act_s> alist = movie.result.act_s;
                ex_act.setAdapter(new MyActAdapter(MainActivity.this,alist));
                ArrayList<Movie.Video_Rec> vlist = movie.result.video_rec;
                video_rec.setAdapter(new MyVideoAdapter(MainActivity.this,vlist));
                super.onPostExecute(s);
            }

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("正在拼命加载........");
            dialog.setMax(100);
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                HttpResponse httpResponse = httpClient.execute(get);
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == 200) {
                    InputStream is = httpResponse.getEntity().getContent();
                    String json = StreamTook.Read(is);
                    return json;


                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }


}
