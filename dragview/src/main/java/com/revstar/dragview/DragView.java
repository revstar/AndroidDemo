package com.revstar.dragview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Create on 2019/7/31 17:10
 * author revstar
 * Email 1967919189@qq.com
 */
public class DragView extends View {

    private int lastX;
    private int lastY;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=x;
                lastY=y;
                break;
                case MotionEvent.ACTION_MOVE:
                    int offX=x-lastX;
                    int offY=y-lastY;
                    layout(getLeft()+offX,getTop()+offY,getRight()+offX,getBottom()+offY);
                    break;

        }
        return true;

    }
}
