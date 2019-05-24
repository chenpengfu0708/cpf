package com.hengtong.led.controller;

import com.hengtong.led.utils.AsyncUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date: 2019/5/24 14:37
 * @author: rain
 * @description: led
 */
@Controller
public class AsyncController {

    @Autowired
    private AsyncUtils asyncUtils;

    @GetMapping("/async")
    @ResponseBody
    public int async(String name, Integer time){
        asyncUtils.timer();
        return 0;
    }
}
