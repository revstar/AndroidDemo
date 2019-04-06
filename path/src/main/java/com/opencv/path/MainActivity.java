package com.opencv.path;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    PathView mPathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPathView=findViewById(R.id.path_view);
        Bitmap photoBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.photo);
        mPathView.setPhotoBitmap(photoBitmap);
    }
}
