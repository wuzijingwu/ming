package com.andy.share;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 类描述：
 * 创建人：郝健澄
 * 创建时间：2017/7/7 11:48
 */
public class QQOauthUtils {
    private UMShareAPI umShareAPI;
    private UMAuthListener mUMAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(App.getApp(), "Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText( App.getApp(), "Authorize fail", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(App.getApp(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    public QQOauthUtils(){
        umShareAPI=App.getApp().getUMShareAPI();
    }

    public void qqLogin(Activity activity){
        if(umShareAPI.isInstall(activity,SHARE_MEDIA.QQ)){
            umShareAPI.doOauthVerify(activity, SHARE_MEDIA.QQ,mUMAuthListener);
            Toast.makeText(App.getApp(), "已经安装QQ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(App.getApp(), "没有安装QQ", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 此方法需要在activity中的onActivityResult调用一下
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        umShareAPI.onActivityResult(requestCode,resultCode,data);
    }
}
