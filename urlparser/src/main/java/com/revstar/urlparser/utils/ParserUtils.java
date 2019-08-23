package com.revstar.urlparser.utils;

import android.util.Log;
import android.widget.Button;

import com.revstar.urlparser.UrlParserActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Create on 2019/8/22 15:04
 * author revstar
 * Email 1967919189@qq.com
 */
public class ParserUtils {

    /**
     * 解析天猫页面
     *
     * @param html
     * @return
     */
    public static ArrayList<String> parserTMUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements src = doc.select("a.item");
        for (Element element : src) {
            Elements data = element.getElementsByTag("img");
            String srcData = data.attr("data-src");
            String aria = data.attr("aria-label");
            if (aria.equals("商品主图")) {
                if (!srcData.isEmpty()) {
                    urlList.add("https:" + srcData);
                }
            }
        }

        return urlList;
    }

    /**
     *淘宝
     * @param html
     * @return
     */

    public static ArrayList<String> parserMTAOBAOUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        try {
            Document doc=   Jsoup.parse(html);
            Elements swiper=doc.select("div.img-wrapper");
            for (Element slide:swiper){
                Elements src=slide.getElementsByTag("img");
                for (Element srcUrl:src){
                    String url=srcUrl.attr("src");
                    if (url!=null&&!url.isEmpty()){
                        urlList.add("https:"+url);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }

    /**
     * 聚划算
     * @param html
     * @return
     */
    public static ArrayList<String> parserJUTAOBAOUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        try {
            Document doc=   Jsoup.parse(html);
            Elements swiper=doc.select("ul.ks-xslide-layer");
            for (Element slide:swiper){
                Elements src=slide.getElementsByTag("img");
                for (Element srcUrl:src){
                    String url=srcUrl.attr("data-src");
                    if (url!=null&&!url.isEmpty()){
                        urlList.add(url);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }


    /**
     * 解析京东页面
     *
     * @param html
     * @return
     */
    public static ArrayList<String> parserJDUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements src = doc.select("ul.pic_list");
        for (Element element : src) {
            Elements srcData = element.select("img");
            for (Element urlEle : srcData) {
                String url;
                //第一张图
                if (urlEle.attr("id").equals("firstImg")) {
                    url = urlEle.attr("src");
                } else {
                    url = urlEle.attr("back_src");
                }
                if (!url.isEmpty()) {
                    urlList.add("https:" + url);
                }
            }
        }

        return urlList;
    }

    /**
     * 微店
     * @param html
     * @return
     */
    public static ArrayList<String> parserWDUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements src = doc.select("div.swipe-items-wrap");
        for (Element element : src) {

            Elements imgElements = element.getElementsByTag("img");
            for (Element imgSrc : imgElements) {
                //第一张图
                String url = imgSrc.attr("src");
                if (url != null && !url.isEmpty()) {
                    urlList.add( url);
                }
            }

        }

        return urlList;
    }

    /**
     * 苏宁
     * @param html
     * @return
     */
 public static ArrayList<String> parserSUNINGUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

     Elements src = doc.select("div.pic-item");

            for (Element element : src) {

                Elements imgElements = element.getElementsByTag("img");
                for (Element imgSrc : imgElements) {
                    //第一张图
                    String url = imgSrc.attr("data-src");
                    if (url == null || url.isEmpty()) {
                        url = imgSrc.attr("ori-src");
                    }
                    if (url!=null&&!url.isEmpty()){
                        urlList.add( "https:"+url);
                    }
                }

            }
            //移除重复项
            if (urlList.size()>=2){
                urlList.remove(1);
            }


        return urlList;
    }

    /**
     * 其他
     * @param html
     * @return
     */
    public static ArrayList<String> parserOtherUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        Elements src = doc.getElementsByTag("img");

        for (Element element : src) {
                //第一张图
                String url = element.attr("src");
                if (url!=null&&!url.isEmpty()){
                    urlList.add( "https:"+url);
                }
            }



        return urlList;
    }

    /**
     * 唯品会
     * @return
     */
    public static ArrayList<String> parserVIPHUrl(String httpUrl) {
        ArrayList<String> urlList = new ArrayList<>();
        if (httpUrl!=null){
            httpUrl= httpUrl.replace("https://m.vip.com/product","https://detail.vip.com/detail").replace("?source=www","");

            try {
             Document doc=   Jsoup.connect(httpUrl).get();
                Elements zoomPad=doc.select("div.pic-sliderwrap");
                for (Element img:zoomPad){
                    Elements slide=img.getElementsByTag("img");
                    for (Element src:slide){
                        String url=src.attr("data-original");
                        if (url != null && !url.isEmpty()) {
                            urlList.add("https:"+ url);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urlList;
    }

    /**
     * 国美
     * @param html
     * @return
     */
    public static ArrayList<String> parserGMHUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
            try {
                Document doc=   Jsoup.parse(html);
                Elements swiper=doc.select("section.big_img_wrapper");
                for (Element slide:swiper){
                    Elements src=slide.getElementsByTag("img");
                    for (Element srcUrl:src){
                        String url=srcUrl.attr("src");
                        if (url!=null&&!url.isEmpty()){
                            urlList.add("https:"+url);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        return urlList;
    }





}