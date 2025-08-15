package com.yys.yunpicture.demos.web.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 28142
 * @description 微信配置
 * @date 2025/7/18 10:43
 */
@Data
@Configuration
public class WechatProperties {

    /**
     * 微信appid
     */
    @Value("${wx.customer.appid}")
    private String appid;

    /**
     * 微信secret
     */
    @Value("${wx.customer.secret}")
    private String secret;
}
