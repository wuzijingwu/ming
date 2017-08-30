package com.bawei.qqdang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.share.QQOauthUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    private QQOauthUtils qq;
    private TextView tv;
    private EditText et_phone_number;
    private EditText et_verification_code;
    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        tv= (TextView) findViewById(R.id.tv);
        TextView tv_show_verification_pager = (TextView) findViewById(R.id.tv_show_verification_pager);
        TextView tv_get_verification_code = (TextView) findViewById(R.id.tv_get_verification_code);
        TextView tv_verification_code = (TextView) findViewById(R.id.tv_verification_code);
        tv_show_verification_pager.setOnClickListener(this);
        tv_get_verification_code.setOnClickListener(this);
        tv_verification_code.setOnClickListener(this);
        qq = new QQOauthUtils();
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_verification_code = (EditText) findViewById(R.id.et_verification_code);
        getmenu();

        //注册短信回调
        SMSSDK.registerEventHandler(eh);

    }

    private void getmenu() {
        slidingMenu=new SlidingMenu(this);

        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setBehindOffset(300);
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slindingmenu);
    }


    public void getqq(View v) {
        qq.qqLogin(this);
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (i == 0) {
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                } else if (i == 2) {
                    String s = map.get("iconurl");
                    String t=map.get("name");
                    x.image().bind(iv, s);
                    tv.setText(t);

                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        qq.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_show_verification_pager:

//打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                        }
                    }
                });
                registerPage.show(MainActivity.this);
                break;
            case R.id.tv_get_verification_code:
                SMSSDK.getVerificationCode("86", et_phone_number.getText().toString().trim(), new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        return false;
                    }
                });

                break;
            case R.id.tv_verification_code:
                SMSSDK.submitVerificationCode("86", et_phone_number.getText().toString().trim(), et_verification_code.getText().toString().trim());
                break;
        }
        
    }
    EventHandler eh = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码正确的回调
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
                Log.e("tag", ((Throwable) data).getMessage().toString());
                //获取验证码成功
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
    public void getce(View v){
        slidingMenu.toggle();

        if (slidingMenu.isMenuShowing()){
            slidingMenu.showContent();
        }else {
            slidingMenu.showMenu();
        }

    }
}
