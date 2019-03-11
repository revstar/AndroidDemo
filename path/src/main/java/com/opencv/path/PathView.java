package com.opencv.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.regex.Matcher;

import androidx.annotation.Nullable;

/**
 * Create on 2019/3/11 9:42
 * author revstar
 * Email 1967919189@qq.com
 */
public class PathView extends View {

    Paint mPaint;
    Path mPath;

    private float eventX,eventY;
    private int endX,endY;


    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public void init() {
        mPaint = new Paint();
        mPaint.setTextSize(100);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //直线
//        mPath.moveTo(100,200);
//        mPath.lineTo(200,200);
//        mPath.lineTo(300,400);
//        mPath.lineTo(0,400);
////        mPath.close();
//        canvas.drawPath(mPath,mPaint);
        //
//        RectF rectF=new RectF(100,200,400,400);
//        mPath.addRect(rectF,Path.Direction.CCW);
//
//        canvas.drawPath(mPath,mPaint);
//
//        RectF rectF1=new RectF(500,200,800,400);
//        mPath.addRect(rectF1,Path.Direction.CW);
//
//        canvas.drawPath(mPath,mPaint);

//        mPath.moveTo(100, 300);
//        mPath.quadTo(200, 200, 300, 300);
////        mPath.quadTo(400,400,500,300);
//        canvas.drawPath(mPath, mPaint);
//        mPath.close();

        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                eventX=event.getX();
                eventY=event.getY();
                mPath.moveTo(eventX,eventY);
                break;

            case MotionEvent.ACTION_MOVE:
                float endX=(event.getX()-eventX)/2+eventX;
                float endY=(event.getY()-eventY)/2+eventY;
                mPath.quadTo(eventX,eventY,endX,endY);
                eventX=event.getX();
                eventY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mPath.close();
                break;

        }
        invalidate();
        return true;
    }



}
