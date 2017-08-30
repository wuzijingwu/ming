package com.example.wuzijing20170722rikao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by dell on 2017/7/22.
 */

public class Secondactivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://www.baidu.com");



    }
}
