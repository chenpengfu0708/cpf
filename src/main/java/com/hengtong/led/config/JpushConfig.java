package com.hengtong.led.config;


import cn.jpush.api.JPushClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class JpushConfig {

    /**
     * 极光官网-个人管理中心-appkey
     * https://www.jiguang.cn/
     */
    @Value("${jpush.appKey}")
    private String appkey;

    /**
     * 极光官网-个人管理中心-点击查看-secret
     */
    @Value("${jpush.masterSecret}")
    private String secret;


    /***
     * yml配置案例：
     * push:
     *   appkey: ed8a79344a04217ee343xxxx
     *   secret: e2a54e1432bcc7eaee64xxxx
     */


    private JPushClient jPushClient;

    /**
     * 推送客户端
     * @return
     */
    @PostConstruct
    public void initJPushClient() {
        jPushClient = new JPushClient(secret, appkey);
    }

    /**
     * 获取推送客户端
     * @return
     */
    public JPushClient getJPushClient() {
        return jPushClient;
    }

}
