package com.vivek.task_2_onsites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.appcompat.widget.ContentFrameLayout;

import java.math.BigInteger;

public class myCanvas extends View  {

    Path path;
    Paint paint;
    int currentX;
    int currentY;
    int motionTouchX;
    int motionTouchY;
    int touchTolerence;



    private fragment_listener fragmentListener;


    public myCanvas(Context context,fragment_listener Flistener) {
        super(context);

        this.fragmentListener = Flistener;
        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.BLUE);
        touchTolerence = ViewConfiguration.get(context).getScaledTouchSlop();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        motionTouchX = (int) event.getX();
        motionTouchY = (int) event.getY();

//
//        Log.d("checkX", String.valueOf(motionTouchX));
//        Log.d("checkY", String.valueOf(motionTouchY));
//        Log.d("checkAction", String.valueOf(event.getAction()));

        fragmentListener.setPath(motionTouchX,motionTouchY,event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                path.moveTo(motionTouchX, motionTouchY);
                currentX = motionTouchX;
                currentY = motionTouchY;
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                int dx = Math.abs(currentX - motionTouchX);
                int dy = Math.abs(currentY - motionTouchY);

                if (dy >= touchTolerence || dx >= touchTolerence) {

                    path.quadTo(currentX, currentY, (motionTouchX + currentX) / 2, (motionTouchY + currentY) / 2);
                    currentY = motionTouchY;
                    currentX = motionTouchX;
                    invalidate();


                }


            }
            break;

            case MotionEvent.ACTION_UP: {


            }
            break;
        }

        return true;
    }

    public void setPath(int x,int y,int action){
        motionTouchX = x;
        motionTouchY = y;

//
//        Log.d("checkX", String.valueOf(motionTouchX));
//        Log.d("checkY", String.valueOf(motionTouchY));
//        Log.d("checkAction", String.valueOf(event.getAction()));

        switch (action) {
            case MotionEvent.ACTION_DOWN: {

                path.moveTo(motionTouchX, motionTouchY);
                currentX = motionTouchX;
                currentY = motionTouchY;
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                int dx = Math.abs(currentX - motionTouchX);
                int dy = Math.abs(currentY - motionTouchY);

                if (dy >= touchTolerence || dx >= touchTolerence) {

                    path.quadTo(currentX, currentY, (motionTouchX + currentX) / 2, (motionTouchY + currentY) / 2);
                    currentY = motionTouchY;
                    currentX = motionTouchX;
                    invalidate();


                }


            }
            break;

            case MotionEvent.ACTION_UP: {


            }
            break;
        }
    }
}
