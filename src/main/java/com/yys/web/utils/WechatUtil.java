package com.yys.web.utils;

/**
 * @author 28142
 * @description 微信工具类
 * @date 2025/7/18 10:56
 */
public class WechatUtil {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static String getAccessToken(String appid, String secret, String code) {
        String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        // String result = HttpUtils.get(url);
        return null;
    }
}
