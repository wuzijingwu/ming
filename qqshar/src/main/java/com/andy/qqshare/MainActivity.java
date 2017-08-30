package com.andy.qqshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.andy.share.QQOauthUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private QQOauthUtils mQQOauthUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQQOauthUtils=new QQOauthUtils();
    }

    @Override
    public void onClick(View v) {
        mQQOauthUtils.qqLogin(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mQQOauthUtils.onActivityResult(requestCode,resultCode,data);
    }
}
