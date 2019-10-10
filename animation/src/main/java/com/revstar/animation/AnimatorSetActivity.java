package com.revstar.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AnimatorSetActivity extends AppCompatActivity {

    private Button btn;
    private Button btn2;
    private List<Animator> mAnimatorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        ObjectAnimator btnBgAnimator = ObjectAnimator.ofInt(btn, "BackgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);

        btnBgAnimator.setDuration(2000);

        ObjectAnimator btnTranslateY = ObjectAnimator.ofFloat(btn, "translationY",
                0, 400, 0);
        btnTranslateY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator btn2TranslateY = ObjectAnimator.ofFloat(btn2, "translationY",
                0, 400, 100);
        btn2TranslateY.setRepeatMode(ValueAnimator.RESTART);
        btn2TranslateY.setRepeatCount(ValueAnimator.INFINITE);


//        AnimatorSet animatorSet=new AnimatorSet();
////        animatorSet.playSequentially(btnBgAnimator,btnTranslateY,btn2TranslateY);
//        if (mAnimatorList==null){
//            mAnimatorList=new ArrayList<>();
//        }
//        mAnimatorList.add(btnBgAnimator);
//        mAnimatorList.add(btnTranslateY);
//        mAnimatorList.add(btn2TranslateY);
//
//        animatorSet.playTogether(mAnimatorList);
//        animatorSet.setDuration(1000);
//        animatorSet.start();
        AnimatorSet animatorSet = new AnimatorSet();
       animatorSet.play(btnTranslateY).after(btnBgAnimator);
       animatorSet.start();



    }
}
