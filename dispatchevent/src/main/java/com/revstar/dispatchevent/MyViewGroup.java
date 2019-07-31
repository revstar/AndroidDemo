package com.revstar.dispatchevent;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Create on 2019/6/24 17:45
 * author revstar
 * Email 1967919189@qq.com
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
