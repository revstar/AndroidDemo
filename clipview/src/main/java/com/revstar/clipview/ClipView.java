package com.revstar.clipview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create on 2019/8/6 16:48
 * author revstar
 * Email 1967919189@qq.com
 */
public class ClipView extends View {
    private RectF r;
    private Paint p;
    private RectF temp;
    public ClipView(Context context) {
        super(context);
        init();
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        r=new RectF();
        p=new Paint();
        p.setColor(Color.WHITE);

        temp=new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        r.set(getPaddingLeft(),getPaddingTop(),getWidth()-getPaddingRight(),getHeight()-getPaddingBottom());
        int corner=32;
        p.setStrokeWidth(4);

        int radio=10;
        //画一层
        canvas.drawRect(r,p);
        canvas.save();
        //裁剪
        temp.set(r);
        temp.inset(corner,-corner);
        canvas.clipRect(temp, Region.Op.DIFFERENCE);

        //裁剪
        temp.set(r);
        temp.inset(-corner,corner);
        canvas.clipRect(temp, Region.Op.DIFFERENCE);

        //加粗 再画一层
        p.setStrokeWidth(p.getStrokeWidth()*2);
        canvas.drawRect(r,p);

        canvas.restore();
    }
}
