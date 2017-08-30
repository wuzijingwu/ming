package com.example.wuzijing20170804;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout framelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        framelay = (FrameLayout) findViewById(R.id.framelay);
        Myview myview = new Myview(this);
        framelay.addView(myview);
    }
}
