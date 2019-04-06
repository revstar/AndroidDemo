package com.revstar.dashpath;

/**
 * Create on 2019-04-06 18:31
 * author revstar
 * Email 1967919189@qq.com
 */
import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomView extends ImageView{

    //画笔
    private Paint linePaint;
    //路径效果
    private PathEffect pathEffect;
    //路径对象
    private Path mPath;
    private float phase;

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        linePaint=new Paint();
    }

    public CustomView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathEffect=new DashPathEffect(new float[] { 10, 15, 20, 10 },
                phase);
        linePaint.setAntiAlias(true);//设置抗锯齿
        linePaint.setColor(Color.RED);//画笔颜色
        linePaint.setStyle(Style.STROKE);//画笔样式
        linePaint.setStrokeWidth(2);//画笔宽度
        linePaint.setPathEffect(pathEffect);//路径动画
        mPath=new Path();
        mPath.moveTo(0, 0);//起点
        mPath.lineTo(getRight()-20, 0);
        mPath.lineTo(getRight()-20, getBottom()-20);
        mPath.lineTo(0, getBottom()-20);
        mPath.close();
        canvas.translate(10, 10);
        canvas.drawPath(mPath, linePaint);
        canvas.translate(10, 10);
    }

    public void setPhase(float phase){
        this.phase=phase;
        postInvalidate();
    }




}
