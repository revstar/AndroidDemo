package com.revstar.urlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               try {
//                   Document doc= Jsoup.connect("https://product.suning.com/0070801979/11314450995.html?safp=d488778a.list.0.a0cab3caa1&safc=prd.0.0").get();
//                   Elements src=doc.select("img[src]");
//                   for (Element element:src){
//                       Attributes node=element.attributes();
//                       Iterator<Attribute> iterator=node.iterator();
//                       while (iterator.hasNext()){
//                           Attribute attribute=iterator.next();
//                           String key=attribute.getKey();
//                           if (key.equals("src")){
//                               String otherSrc=attribute.getValue();
//                               Log.d(MainActivity.this.getLocalClassName()+">>>>>>图片地址:",otherSrc);
//                           }
//                       }
//                   }
//               } catch (IOException e) {
//                   e.printStackTrace();
//                   Toast.makeText(MainActivity.this,"解析错误:"+e.getMessage(),Toast.LENGTH_SHORT).show();
//               }
//           }
//       }).start();


    }
}
