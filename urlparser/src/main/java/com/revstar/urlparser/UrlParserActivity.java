package com.revstar.urlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UrlParserActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private ImageView iGPrevious, iGNext;
    private Button btnImport;
    private ArrayList<String>urlList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_parser);
        init();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        mWebView = findViewById(R.id.web_view);
        iGPrevious = findViewById(R.id.ig_previous);
        iGNext = findViewById(R.id.ig_next);
        btnImport = findViewById(R.id.btn_import);

        iGPrevious.setOnClickListener(this);
        iGNext.setOnClickListener(this);
        btnImport.setOnClickListener(this);

        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        mWebView.requestFocus();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        mWebView.loadUrl("https://detail.tmall.com/item.htm?spm=a1z10.3-b-s.w4011-14691978066.118.55137a7dppE8gA&id=596039514436&rn=b42c561950ff20f02c0f533becd03020&abbucket=14");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                // 获取页面内容
//                webView.loadUrl("javascript:window.local_obj.showSource('<html>'+" + "document.documentElement.outerHTML.toString()+'</html>');");
                webView.loadUrl("javascript:window.java_obj.showSource("
                        + "document.getElementsByTagName('html')[0].innerHTML);");

                super.onPageFinished(webView, s);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
            }


        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ig_previous:
                break;
            case R.id.ig_next:
                break;
            case R.id.btn_import:
                importResult();
                break;
            default:
                break;
        }
    }

    public void importResult(){
        if (urlList==null){
            Toast.makeText(this,"没有获取到图片结果",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(this,ParserResultActivity.class);
        intent.putStringArrayListExtra("urlList",urlList);
        startActivity(intent);
    }

    public final class InJavaScriptLocalObj  {
        @JavascriptInterface
        public void showSource(String html) {
            parserHtml(html);
            Logger.addLogAdapter(new AndroidLogAdapter());
            Logger.d(html);
//            System.out.println("===>开始===" + html+",===结束===");
        }


    }

    public void parserHtml(final String html) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.parse(html);

//                    Document doc = Jsoup.connect("https://m.tb.cn/h.eQR3EvL?sm=fc6f8d").timeout(60000).method(Connection.Method.GET).followRedirects(false).get();
//                    Document doc=Jsoup.connect("https://detail.m.tmall.com/item.htm?spm=a1z10.5-b-s.w4011-14571687313.115.33eb4fa050sJ5C&id=586689400251&rn=600380b21af0ceee572b94c365201bdb&abbucket=14").get();
                    Elements src = doc.select("a.item");
                    if (urlList==null){
                        urlList=new ArrayList<>();
                    }
                    urlList.clear();
                    for (Element element : src) {
                        Elements data=element.getElementsByTag("img");
                        String srcData=data.attr("data-src");
                        String  aria=data.attr("aria-label");
                        Log.d(UrlParserActivity.this.getLocalClassName() + ">>>>>>图片地址:", srcData);
                        if (aria.equals("商品主图")) {
                            if (!srcData.isEmpty()){
                                urlList.add("https:"+srcData);
                            }
                        }
//                        Attributes node = element.attributes();
//                        Iterator<Attribute> iterator = node.iterator();
//                        while (iterator.hasNext()) {
//                            Attribute attribute = iterator.next();
//                            String key = attribute.getKey();
//
//                        }
                    }
                    btnImport.setClickable(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UrlParserActivity.this, "解析错误:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

}
