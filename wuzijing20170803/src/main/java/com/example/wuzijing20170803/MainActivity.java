package com.example.wuzijing20170803;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout famelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        famelay = (FrameLayout) findViewById(R.id.famelay);
        Mycdd mycdd = new Mycdd(this);
        famelay.addView(mycdd);


    }


}
