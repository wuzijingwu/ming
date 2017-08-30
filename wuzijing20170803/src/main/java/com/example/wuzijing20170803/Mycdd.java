package com.example.wuzijing20170803;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dell on 2017/8/3.
 */

public class Mycdd extends View {

    Paint paint= new Paint();
    PointF pointF=new PointF();



    public Mycdd(Context context) {
        super(context);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setDither(true);




    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(pointF.x,pointF.y,10,paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       if(event.getAction()==MotionEvent.ACTION_DOWN){
           Toast.makeText(getContext(),"按下",Toast.LENGTH_SHORT).show();
       }else if(event.getAction()==MotionEvent.ACTION_MOVE){

           pointF.set(event.getX(),event.getY());

       }

       invalidate();


        return true;





    }
}
