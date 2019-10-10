package com.yskj.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class AccelerateDecelerateInterpolatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerate_decelerate_interpolator);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewSquare=findViewById(R.id.view_square);
                Animation tranlateAnim= AnimationUtils.loadAnimation(AccelerateDecelerateInterpolatorActivity.this,R.anim.move_anim);
                tranlateAnim.setInterpolator(new CycleInterpolator(1));
                viewSquare.startAnimation(tranlateAnim);
            }
        });
    }
}
