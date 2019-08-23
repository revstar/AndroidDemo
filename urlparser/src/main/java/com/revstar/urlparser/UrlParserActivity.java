package com.revstar.urlparser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.revstar.urlparser.utils.NetUtils;
import com.revstar.urlparser.utils.ParserUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import java.util.Vector;

public class UrlParserActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private ImageView iGPrevious, iGNext;
    private Button btnImport;
    private ArrayList<String> urlList;
    private EditText etInputUrl;
    private Button btnSearch;
    private Vector<ArrayList<String> >mVector=new Vector<>();
    private String url = "https://h5.m.taobao.com/?sprefer=sypc00";
//    private String url = "https://m.vip.com/product-0-6918337434724607831.html?goodsId=6918337434724607831&brandId=1710613335&goodsType=0&tra_from=m%3Ai%3A1566464972441_65f77dd576d79e64379f411b92b5bd79%3Ac%3Anature%3Awxclick%3A&from=m&device=i&cid=1566464972441_65f77dd576d79e64379f411b92b5bd79&f=nature%3A0%3A&other=wxclick&mref=";
//    private String url="https://detail.m.tmall.com/item.htm?id=566473612602&scm=1007.12144.135827.1720401_0_0&pvid=9d95e617-35bb-4d85-8900-6197f3ea5d81&utparam={%22x_hestia_source%22:%221720401%22,%22x_object_type%22:%22item%22,%22x_mt%22:8,%22x_src%22:%221720401%22,%22x_pos%22:6,%22x_pvid%22:%229d95e617-35bb-4d85-8900-6197f3ea5d81%22,%22x_object_id%22:566473612602}&spm=a211ue.11501597.new-recommend.7";
//    private String url="https://ju.taobao.com/m/jusp/alone/detailwap/mtp.htm?item_id=593841462450&_force=wap&_target=_blank&spm=a2147.7632989.List.1&ju_id=10000320600379&item_id=593841462450&_format=true";

    private static class MyHandler extends Handler {

        private final WeakReference<UrlParserActivity> mActivityWeakReference;

        private MyHandler(UrlParserActivity activityWeakReference) {

            mActivityWeakReference = new WeakReference<UrlParserActivity>(activityWeakReference);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (mActivityWeakReference.get() != null && mActivityWeakReference.get().btnImport != null) {
                mActivityWeakReference.get().btnImport.setText((CharSequence) msg.obj);
                mActivityWeakReference.get().btnImport.setClickable(true);
            }
        }
    }


    private MyHandler mMyHandler = new MyHandler(this);

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
        etInputUrl = findViewById(R.id.et_input_url);
        btnSearch = findViewById(R.id.btn_search);


        iGPrevious.setOnClickListener(this);
        iGNext.setOnClickListener(this);
        btnImport.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        mWebView.requestFocus();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                if (btnImport != null) {
                    btnImport.setText("解析数据...");
                    btnImport.setClickable(false);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                url = s;
                if (etInputUrl != null) {
                    etInputUrl.setText(url);
                }
                // 获取页面内容
                webView.loadUrl("javascript:window.java_obj.showSource("
                        + "document.getElementsByTagName('html')[0].innerHTML);");

//                mWebView.evaluateJavascript("javascript:window.java_obj.showSource(\"\n" +
//                                "                        + \"document.getElementsByTagName('html')[0].innerHTML);",
//                        new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String html) {
//                                parserHtml(html);
//                                Log.d("HTML", "whd >>html:" + html);
//                            }
//                        });


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
                back();
                break;
            case R.id.ig_next:
                forword();
                break;
            case R.id.btn_import:
                importResult();
                break;
            case R.id.btn_search:
                search();
                break;
            default:
                break;
        }
    }


    private void back() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        }
    }

    private void forword() {
        if (mWebView != null && mWebView.canGoForward()) {
            mWebView.goForward();
        }
    }

    private void search() {
        if (etInputUrl != null) {
            String inputUrl = etInputUrl.getText().toString();
            if (TextUtils.isEmpty(inputUrl)) {
                Toast.makeText(this, "请输入网址", Toast.LENGTH_SHORT).show();
            } else if (!NetUtils.isHttpUrl(inputUrl)) {
                Toast.makeText(this, "请输入正确网址", Toast.LENGTH_SHORT).show();
            } else {
                if (mWebView != null) {
                    this.url = inputUrl;
                    mWebView.loadUrl(url);
                }
            }
        }
    }


    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            parserHtml(html);
            Log.d("html文件:", html);
        }


    }

    public synchronized void importResult() {

        int maxSize=0;
        int maxIndex=0;
        if(mVector!=null){
            for (int i=0;i<mVector.size();i++){
                if (mVector.get(i)!=null&&mVector.get(i).size()>=maxSize){
                    maxSize=mVector.get(i).size();
                    maxIndex=i;
                }
            }
        }

          if (maxSize == 0) {
              Toast.makeText(this, "没有获取到图片结果", Toast.LENGTH_SHORT).show();
              return;
          }
          if (urlList==null){
              urlList=new ArrayList<>();
          }
          urlList.clear();
          urlList.addAll(mVector.get(maxIndex));

          Intent intent = new Intent(this, ParserResultActivity.class);
          intent.putStringArrayListExtra("urlList", urlList);
          startActivity(intent);
      }


    public synchronized  void parserHtml(final String html) {


            try {

                if (NetUtils.isTMUrl(url)) {
                    getUrlList(ParserUtils.parserTMUrl(html));
                } else if (NetUtils.isJDUrl(url)) {
                    getUrlList(ParserUtils.parserJDUrl(html));
                } else if (NetUtils.isWDUrl(url)) {
                    getUrlList(ParserUtils.parserWDUrl(html));
                } else if (NetUtils.isVIPUrl(url)) {
                    getUrlList(ParserUtils.parserVIPHUrl(url));
                }else if (NetUtils.isSUNINGUrl(url)){
                    getUrlList(ParserUtils.parserSUNINGUrl(html));
                }else if (NetUtils.isGMUrl(url)){
                    getUrlList(ParserUtils.parserGMHUrl(html));
                }else if (NetUtils.isMTaobaoUrl(url)){
                    getUrlList(ParserUtils.parserMTAOBAOUrl(html));
                }else if (NetUtils.isJUTaobaoUrl(url)){
                    getUrlList(ParserUtils.parserJUTAOBAOUrl(html));
                }
                else {
                    getUrlList(ParserUtils.parserOtherUrl(html));
                }

                if (mMyHandler != null) {
                    Message msg = new Message();
                    msg.obj = "导入";
                    mMyHandler.sendMessage(msg);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                if (mMyHandler != null) {
                    Message msg = new Message();
                    msg.obj = "解析错误";
                    mMyHandler.sendMessage(msg);
                }
            }
    }

    public    void getUrlList(ArrayList<String>list) {

        try {
            mVector.add(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
