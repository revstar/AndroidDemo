package com.yskj.customview.constructor;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yskj.customview.R;

import androidx.annotation.Nullable;

/**
 * Create on 2019/3/5 14:43
 * author revstar
 * Email 1967919189@qq.com
 */
public class CustomGreenView extends TextView {
    public CustomGreenView(Context context) {
        super(context);

    }

    public CustomGreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public CustomGreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,R.style.GreenTextStyle);
    }

    private CustomGreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
