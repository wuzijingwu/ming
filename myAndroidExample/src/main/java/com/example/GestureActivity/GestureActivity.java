package com.example.GestureActivity;

 

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.R;
import com.example.GestureActivity.GestureUtils.Screen;

public class GestureActivity extends Activity {
    
    private GestureDetector gestureDetector;
    private Screen screen;

 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture1);
        gestureDetector = new GestureDetector(this,onGestureListener);
        //得到屏幕的大小
        screen = GestureUtils.getScreenPix(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    
    GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            //限制必须得划过屏幕的1/3才能算划过
            float x_limit = screen.widthPixels / 3;
            float y_limit = screen.heightPixels / 3;
            float x_abs = Math.abs(x);
            float y_abs = Math.abs(y);
            if(x_abs >= y_abs){
                //gesture left or right
                if(x > x_limit || x < -x_limit){
                    if(x>0){
                        //right
                        show("right");
                    }else if(x<0){
                        //left
                        show("left");
                    }
                }
        
            }else{
                //gesture down or up
                if(y > y_limit || y < -y_limit){
                    if(y>0){
                        //down
                        show("down");
                    }else if(y<0){
                        //up
                        show("up");
                    }
                }
            }
            return true;
        }

    };
    
    private void show(String value){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    
}
