package com.hengtong.led.controller;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.dto.HttpWeiXiongDto;
import com.hengtong.led.dto.WxOpenIdDto;
import com.hengtong.led.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@Slf4j
public class HttpController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.secret}")
    private String secret;


    @GetMapping("/http")
    @ResponseBody
    public HttpWeiXiongDto getByHttp(String url) {
        try {
            Response response = HttpUtils.get(url);
            HttpWeiXiongDto result = JSON.parseObject(response.body(), HttpWeiXiongDto.class);
            log.info("result=" + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpWeiXiongDto().code(1).msg("调用第三方接口失败！");
    }


    /**
     * 根据code获取微信openId和token信息
     */
    @GetMapping("/getWxOpenId")
    @ResponseBody
    public WxOpenIdDto getWxOpenId(String code) {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + appId +
                    "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
            Response response = HttpUtils.get(url);
            System.out.println("response = " + response);
            WxOpenIdDto result = JSON.parseObject(response.body(), WxOpenIdDto.class);
            log.info("result=" + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new WxOpenIdDto();
    }
}
