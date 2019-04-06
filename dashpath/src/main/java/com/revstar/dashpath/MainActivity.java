package com.revstar.dashpath;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomView customView;
    private float pahse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView=(CustomView) findViewById(R.id.customView);
//        handler.sendEmptyMessage(0);
        customView.setPhase(100f);

    }
    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0){
//                pahse+=1;
                customView.setPhase(pahse);
                handler.sendEmptyMessage(0);
            }
        };
    };


}
