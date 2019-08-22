package com.revstar.urlparser.utils;

import android.util.Log;
import android.widget.Button;

import com.revstar.urlparser.UrlParserActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

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
    public static List<String> parserTMUrl(String html) {
        List<String> urlList = new ArrayList<>();
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
     * 唯品会
     * @param html
     * @return
     */
    public static ArrayList<String> parserVIPHUrl(String html) {
        ArrayList<String> urlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements src = doc.select("div.mint-swipe-items-wrap");
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
}
