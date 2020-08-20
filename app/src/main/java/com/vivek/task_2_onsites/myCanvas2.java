package com.vivek.task_2_onsites;

import android.annotation.SuppressLint;
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

public class myCanvas2 extends View{
    Path path;
    Paint paint;
    int currentX;
    int currentY;
    int motionTouchX;
    int motionTouchY;
    Canvas myCanvas1;
    Bitmap myBitmap;
    int touchTolerence;
    Canvas newCanvas;


    public myCanvas2(Context context) {
        super(context);


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
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        invalidate();
        canvas.drawPath(path,paint);
        Log.d("helloOnDraw","hello");

    }



    public void updatePath(int x, int y, int action) {


        motionTouchX = x;
        motionTouchY = y;

        Log.d("checkHereX", String.valueOf(x));
        Log.d("checkY", String.valueOf(y));
        Log.d("checkAction", String.valueOf(action));

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                path.reset();
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



                }


            }
            break;



            case MotionEvent.ACTION_UP: {
                path.reset();

            }
            break;


        }

        invalidate();
        Log.d("helloUpdate","hello");

    }


}
