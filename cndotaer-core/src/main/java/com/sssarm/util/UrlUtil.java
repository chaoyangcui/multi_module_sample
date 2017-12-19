package com.sssarm.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cuiguiyang on 2017/2/16 01:09.
 * Desc
 */
public class UrlUtil {

    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;

    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
     *
     * @param urlStr 指定URL网络地址
     * @return URL
     */
    public synchronized static URL isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return null;
        }
        while (counts < 5) {
            try {
                url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                state = con.getResponseCode();
                System.out.println(counts + "= " + state);
                if (state == 200) {
                    System.out.println("URL可用！");
                }
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
                counts++;
                System.out.println("URL不可用，连接第 " + counts + " 次");
                //urlStr = null;
                continue;
            }
        }
        return url;
    }

    public static void main(String[] args) {
        UrlUtil u = new UrlUtil();
        String url = "http://video.zhibo.umaman.com/zhibo/5875e1c3cff348f03e8b4569_4.m3u8?auth_key=1487060430-0-0-8db780f953f16e005aaaefb6d47def89";
        u.isConnect(url);
    }

}
