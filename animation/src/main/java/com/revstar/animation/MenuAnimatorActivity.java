package com.revstar.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mIsMenuOpen = false;

    private Button item1,item2,item3,item4,item5,itemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnu_animator);

        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        itemClick=findViewById(R.id.item_click);

        itemClick.setOnClickListener(this);
//        item1.setOnClickListener(this);
//        item2.setOnClickListener(this);
//        item3.setOnClickListener(this);
//        item4.setOnClickListener(this);
//        item5.setOnClickListener(this);

    }

    private void openMenu(){
        doAnimateOpen(item1,0,5,300);
        doAnimateOpen(item2,1,5,300);
        doAnimateOpen(item3,2,5,300);
        doAnimateOpen(item4,3,5,300);
        doAnimateOpen(item5,4,5,300);
    }

    private void closeMenu(){
        doAnimateClose(item1,0,5,300);
        doAnimateClose(item2,1,5,300);
        doAnimateClose(item3,2,5,300);
        doAnimateClose(item4,3,5,300);
        doAnimateClose(item5,4,5,300);
    }
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f));
        set.setDuration(500).start();
    }


    private void doAnimateClose(View view,int index,int total,int radius){
        if (view.getVisibility()!=View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree=Math.PI*index/((total-1)*2);
        int translationX= -(int) (radius*Math.sin(degree));
        int translationY= -(int) (radius*Math.cos(degree));

        AnimatorSet set=new AnimatorSet();

        set.playTogether(ObjectAnimator.ofFloat(view,"translationX",translationX,0),
                ObjectAnimator.ofFloat(view,"translationY",translationY,0),
                ObjectAnimator.ofFloat(view,"scaleX",1f,0f),
                ObjectAnimator.ofFloat(view,"scaleY",1f,0f),
                ObjectAnimator.ofFloat(view,"alpha",1f,0f));
        set.setDuration(500).start();


    }
    @Override
    public void onClick(View v) {
        Toast.makeText(this,"你单击了:"+v,Toast.LENGTH_SHORT).show();
        if (!mIsMenuOpen){
            mIsMenuOpen=true;
            openMenu();
        }
        else {
            mIsMenuOpen=false;
            closeMenu();
        }

    }

}
