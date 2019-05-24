package com.hengtong.led.controller;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.dto.HttpWeiXiongDto;
import com.hengtong.led.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @date: 2019/5/23 11:48
 * @author: rain
 * @description: led
 */
@Controller
@Slf4j
public class HttpController {


    @GetMapping("/http")
    @ResponseBody
    public HttpWeiXiongDto getByHttp(String url){
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
}
