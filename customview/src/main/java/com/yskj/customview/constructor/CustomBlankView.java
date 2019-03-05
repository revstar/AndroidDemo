package com.yskj.customview.constructor;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yskj.customview.R;

import androidx.annotation.Nullable;

/**
 * Create on 2019/3/5 14:42
 * author revstar
 * Email 1967919189@qq.com
 */
public class CustomBlankView extends TextView {

    public CustomBlankView(Context context) {
        super(context);
    }

    public CustomBlankView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,R.style.OrangeTextStyle);

    }

    public CustomBlankView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, R.style.OrangeTextStyle,0);
    }

    private CustomBlankView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
