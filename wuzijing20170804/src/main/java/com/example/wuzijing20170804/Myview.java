package com.example.wuzijing20170804;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by dell on 2017/8/4.
 */

public class Myview extends View {
    Paint paint = new Paint();
    PointF pointF = new PointF();
    private int startx;
    private int starty;
    private boolean moveable;
//    private int  pointF.x=mRadius;
//    private int  pointF.y=;


    public Myview(Context context) {
        super(context);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setDither(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(pointF.x, pointF.y, 20, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startx = x;
            starty = y;
            if (x > pointF.x - 20 && x < pointF.x + 20 && y > pointF.y - 20 && y < pointF.y + 20) {
                moveable = true;
                Toast.makeText(getContext(), "触摸事件x的坐标" + startx, Toast.LENGTH_SHORT).show();
            } else {
                moveable = false;
            }


        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            pointF.set(event.getX(), event.getY());
            invalidate();
        }
        return true;
    }
}
