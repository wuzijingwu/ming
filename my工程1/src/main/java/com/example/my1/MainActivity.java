package com.example.my1;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= new Intent();
        ComponentName componentName=new ComponentName("com.example.my2","com.example.my2.MainActivity");
        intent.setComponent(componentName);
        startActivity(intent);

    }
}
