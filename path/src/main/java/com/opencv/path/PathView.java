package com.opencv.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
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

    /**
     * 圈选的画笔
     */
    Paint mPaint;
    /**
     * 圈选路径
     */
    Path mPath;

    /**
     * 触摸点的坐标
     */
    private float eventX,eventY;
    /**
     * 原始照片图片
     */
    private Bitmap mPhotoBitmap;

    /**
     * 裁剪的照片
     */
    private Bitmap mCutImvBmp;
    /**
     *  贝塞尔曲线区域
     */
    RectF quadPathRectF;
    /**
     * 抠出来的图
     */
    Bitmap mCutOutBmp;
    private int endX,endY;

    private boolean mCutFinFlag = false;

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

    public void setPhotoBitmap(Bitmap photoBitmap){
        if (photoBitmap==null){
            return;
        }
        mPhotoBitmap=photoBitmap;
    }

    public void init() {
        mPaint = new Paint();
        PorterDuffXfermode xfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_IN );
//        mPaint.setXfermode(xfermode);
        mPaint.setTextSize(100);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPhotoBitmap==null){
            return;
        }

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mPhotoBitmap,0,0,mPaint);
        canvas.drawCircle(30,30,50,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint.setStyle(Paint.Style.STROKE);
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
                mCutFinFlag=true;
                break;


        }
        if (mCutFinFlag){

            quadPathRectF = new RectF();
            mPath.computeBounds(  quadPathRectF,   false);
            mPaint.setStyle(Paint.Style.FILL);
            Canvas mCanvas=new Canvas();
            mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            mCanvas.drawPath(  mPath,   mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            mCanvas.drawBitmap(  mPhotoBitmap, 0.0f, 0.0f,   mPaint);
            mCutOutBmp = Bitmap.createBitmap((int)   quadPathRectF.width(), (int)   quadPathRectF.height(),   mPhotoBitmap.getConfig());
            Canvas mCanvasCut = new Canvas(  mCutOutBmp);
//                        mCanvasCut.drawColor(0, Mode.CLEAR);
            mCanvasCut.drawBitmap(  mCutOutBmp, new Rect((int)   quadPathRectF.left, (int)   quadPathRectF.top, ((int)   quadPathRectF.left) + ((int)   quadPathRectF.width()), ((int)   quadPathRectF.top) + ((int)   quadPathRectF.height())), new RectF(0.0f, 0.0f, (float)   mCutOutBmp.getWidth(), (float)   mCutOutBmp.getHeight()), null);
        }
        invalidate();
        return true;
    }



}
