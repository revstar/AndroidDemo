package com.revstar.urlparser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create on 2019/5/10 14:19
 * author revstar
 * Email 1967919189@qq.com
 */
public class NetUtils {
    /**
     * 判断字符串是否为URL
     *
     * @param urls 用户头像key
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }

    /**
     * 判断是否为淘宝页面
     *
     * @param url
     * @return
     */
    public static boolean isMTaobaoUrl(String url) {
        return (url != null && url.contains("m.taobao.com"));
    }

    /**
     * 聚划算
     * @param url
     * @return
     */
  public static boolean isJUTaobaoUrl(String url) {
        return (url != null && url.contains("ju.taobao.com"));
    }

    /**
     * 判断是否为天猫页面
     *
     * @param ur
     * @return
     */
    public static boolean isTMUrl(String ur) {
        return (ur != null && ur.contains("tmall.com"));
    }


    /**
     * 判断是否为京东页面
     *
     * @param ur
     * @return
     */
    public static boolean isJDUrl(String ur) {
        return (ur != null && ur.contains("jd.com"));
    }

    /**
     * 判断是否为微店
     *
     * @param ur
     * @return
     */
    public static boolean isWDUrl(String ur) {
        return (ur != null && ur.contains("weidian.com"));
    }

    /**
     * 唯品会
     * @param ur
     * @return
     */
    public static boolean isVIPUrl(String ur) {
        return (ur != null && ur.contains("vip.com"));
    }

    /**
     * 苏宁
     * @param ur
     * @return
     */
    public static boolean isSUNINGUrl(String ur) {
        return (ur != null && ur.contains("suning.com"));
    }

    /**
     * 国美
     * @param ur
     * @return
     */
    public static boolean isGMUrl(String ur) {
        return (ur != null && ur.contains("gome.com"));
    }

    /**
     * 拼多多
     * @param ur
     * @return
     */
    public static boolean isPDD(String ur) {
        return (ur != null && ur.contains("yangkeduo.com"));
    }




}
