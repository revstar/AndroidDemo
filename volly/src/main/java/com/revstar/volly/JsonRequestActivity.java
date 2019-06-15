package com.revstar.volly;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revstar.volly.model.IpModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonRequestActivity extends AppCompatActivity {

    TextView tvIp;
    ImageView igPhoto;
    RequestQueue mQueue;
    ImageView netWorkImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);
        tvIp = findViewById(R.id.tvIp);
        igPhoto = findViewById(R.id.ig_photo);
        netWorkImageView = findViewById(R.id.network_image_view);

//        netWorkImageView.s;

        new Thread(new Runnable() {
            @Override
            public void run() {
                getIp();
//                imageRequest();
                imageLoader();

            }
        }).start();

    }

    public void getIp() {
//        Map<String,String>merchant=new HashMap<>();
//        merchant.put("ip","59.108.54.37");
//
//        JSONObject jsonObject=new JSONObject(merchant);
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "http://gank.io/api/today", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                IpModel ipModel = new Gson().fromJson(response.toString(), IpModel.class);
                if (ipModel != null) {
                    tvIp.setText(ipModel.getCategory().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(getApplicationContext());
        }
        mQueue.add(mJsonObjectRequest);
    }


    public void imageRequest() {
        ImageRequest imageRequest = new ImageRequest("https://ww1.sinaimg.cn/large/0073sXn7gy1g37vxr8675j30u01hcdlc", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                igPhoto.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(getApplicationContext());
        }
        mQueue.add(imageRequest);
    }


    public void imageLoader() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(getApplicationContext());
            ImageLoader imageLoader = new ImageLoader(mQueue,null);

            ImageLoader.ImageListener listener = ImageLoader.getImageListener(igPhoto, R.id.ig_photo, R.id.ig_photo);
            imageLoader.get("https://ww1.sinaimg.cn/large/0073sXn7gy1g37vxr8675j30u01hcdlc", listener);

        }
    }

}
