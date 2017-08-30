package com.example.lian1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import cz.jalasoft.net.http.HttpClient;

/**
 * Created by dell on 2017/7/12.
 */

public class Fragment1 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view1 = view.findViewById(R.id.image1);
        getimages();

    }

    public void getimages(String path){
        new AsyncTask<String,Void,String>(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String path = params[0];

//                    HttpClient httpClient=new HttpClient(
                    HttpClient httpclicen=new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(path);
                    HttpResponse execute = httpclicen.execute(httpGet);
                    int code = execute.getStatusLine().getStatusCode();
                    if(code==200){
                        InputStream is = execute.getEntity().getContent();



                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        }.execute(path);



    }


}
