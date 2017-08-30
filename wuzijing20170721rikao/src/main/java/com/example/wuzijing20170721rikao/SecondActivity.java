package com.example.wuzijing20170721rikao;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by dell on 2017/7/21.
 */

public class SecondActivity extends Activity {


    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexond);
        webView = (WebView) findViewById(R.id.webwiew);
        webView.loadUrl("http://www.baidu.com");


    }
}
