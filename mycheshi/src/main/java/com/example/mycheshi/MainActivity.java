package com.example.mycheshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new Myadapter());

    }
    class Myadapter extends BaseAdapter{
        @Override
         public int getCount() {

             return 0;
         }

         @Override
         public Object getItem(int position) {

             return null;
         }

         @Override
         public long getItemId(int position) {

             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {

             return null;
         }


    }


    


}
