package com.example.mya1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by dell on 2017/7/25.
 */

public class ThreeActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeactivity);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://toutiao.com/group/6444776844335907085");

    }
}
