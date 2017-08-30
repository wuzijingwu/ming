package com.example.scolerday1;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andy.share.QQOauthUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.x;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private SlidingMenu slidingMenu;
    private ImageView sliding_menu;
    private ImageView qQdenglu;
    private QQOauthUtils qq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        sliding_menu = (ImageView) findViewById(R.id.toggle_sliding_menu);
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(50);
        slidingMenu.setFadeDegree(0.5f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu_layout);
        sliding_menu.setOnClickListener(this);

        qQdenglu = (ImageView) findViewById(R.id.QQdenglu);
        qq=new QQOauthUtils();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toggle_sliding_menu:
                slidingMenu.toggle();
                if (slidingMenu.isMenuShowing()) {
                    slidingMenu.showContent();
                } else {
                    slidingMenu.showMenu();
                }
                break;





        }
    }
    public void denglu(View v){
        qq.qqLogin(this);
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (i==0){
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                }else if (i==2){
                    String s = map.get("iconurl");
                    x.image().bind(qQdenglu,s);
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


    }





