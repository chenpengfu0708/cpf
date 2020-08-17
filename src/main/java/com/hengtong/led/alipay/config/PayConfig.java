package com.hengtong.led.alipay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 支付配置类
 *
 * @author column
 */
@Component
@PropertySource(value = "classpath:alipayConfig.properties") //读取指定配置文件
public class PayConfig {


    public static String app_id;

    public static String merchant_private_key;

    public static String alipay_public_key;

    public static String notify_url;

    public static String return_url;

    public static String sign_type;

    public static String charset;

    public static String gatewayUrl;

    public static String log_path;




    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Value("${appid}")
    public void setApp_id(String app_id) {
        PayConfig.app_id = app_id;
    }

    @Value("${privateKey}")
    public void setMerchant_private_key(String merchant_private_key) {
        PayConfig.merchant_private_key = merchant_private_key;
    }

    @Value("${publicKey}")
    public void setAlipay_public_key(String alipay_public_key) {
        PayConfig.alipay_public_key = alipay_public_key;
    }

    @Value("${notifyUrl}")
    public void setNotify_url(String notify_url) {
        PayConfig.notify_url = notify_url;
    }

    @Value("${returnUrl}")
    public void setReturn_url(String return_url) {
        PayConfig.return_url = return_url;
    }

    @Value("${signType}")
    public void setSign_type(String sign_type) {
        PayConfig.sign_type = sign_type;
    }

    @Value("${charset}")
    public void setCharset(String charset) {
        PayConfig.charset = charset;
    }

    @Value("${gatewayUrl}")
    public void setGatewayUrl(String gatewayUrl) {
        PayConfig.gatewayUrl = gatewayUrl;
    }

    @Value("${logPath}")
    public void setLog_path(String log_path) {
        PayConfig.log_path = log_path;
    }
}

