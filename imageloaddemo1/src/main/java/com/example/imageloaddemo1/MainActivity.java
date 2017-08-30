package com.example.imageloaddemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path="http://img.juhe.cn/cookbook/s/1/45_b6d7329b703f6e85.jpg";
        ImageView image= (ImageView) findViewById(R.id.images);




    }
}
