package com.yskj.customview.constructor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yskj.customview.R;

import androidx.annotation.Nullable;

/**
 * Create on 2019/3/5 17:59
 * author revstar
 * Email 1967919189@qq.com
 */
public class CustomRedView extends Button {

    public CustomRedView(Context context) {
        this(context,null);
    }

    public CustomRedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,R.attr.myTextViewStyle);
    }

    public CustomRedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,R.style.ReadTextStyle);
    }

    public CustomRedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
