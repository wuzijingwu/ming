package com.example.scolerday1;

import org.xutils.x;

/**
 * Created by dell on 2017/8/4.
 */

public class App extends com.andy.share.App {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);


    }
}
