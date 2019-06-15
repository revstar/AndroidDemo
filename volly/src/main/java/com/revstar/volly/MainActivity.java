package com.revstar.volly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity>>>";

    TextView tvResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResponse=findViewById(R.id.tv_response);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBaidu();
            }
        }).start();

    }
    public  void getBaidu(){
        RequestQueue mQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest mStringRequest=new StringRequest(Request.Method.GET, "https://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,response);

                Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
                tvResponse.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.getMessage(),error);
            }
        });
        mQueue.add(mStringRequest);
    }
}
