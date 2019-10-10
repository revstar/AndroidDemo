package com.revstar.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class CameraStretchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_stretch);
        ImageView igStretch=findViewById(R.id.ig_stretch);
        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,2.0f,1.0f,2.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
        igStretch.startAnimation(scaleAnimation);
    }
}
