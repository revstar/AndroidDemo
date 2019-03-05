package com.yskj.customview.constructor;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.yskj.customview.R;

import androidx.annotation.Nullable;

/**
 * Create on 2019/3/5 11:01
 * author revstar
 * Email 1967919189@qq.com
 */
public class CustomView extends Button {

    public static final String TAG="MyView";

    public CustomView(Context context) {
        this(context,null);
        Log.d(TAG,"First Construtor");
    }

    public CustomView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {

        this(context, attrs,R.attr.myTextViewStyle);
        Log.d(TAG,"Second Constructor");
        for (int i=0;i< attrs.getAttributeCount();i++){
            Log.d(TAG,attrs.getAttributeName(i)+"   "+attrs.getAttributeValue(i));
        }

    }

    public CustomView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        Log.d(TAG,"Third Construtor");
    }

    private CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG,"Fourth Constructor");
    }
}
