package com.yskj.customview.constructor;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create on 2019/6/27 15:57
 * author revstar
 * Email 1967919189@qq.com
 */
public class CenterViewGroup extends ViewGroup {

    public CenterViewGroup(Context context) {
        super(context);
    }

    public CenterViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumHeight(),widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i=0;i<getChildCount();i++){
            View child=getChildAt(i);
            int width=child.getMeasuredWidth();
            int height=child.getMeasuredHeight();

            int mLeft=(r-width)/2;
            int mTop=(b-height)/2;
            int mRight=mLeft+width;
            int mBottom=mTop+height;
            child.layout(mLeft,mTop,mRight,mBottom);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        for (int i=0;i<getChildCount();i++){
//            View child=getChildAt(i);
//            child.draw(canvas);
//        }
    }
}
