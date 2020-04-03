package com.hengtong.led.controller;

import com.hengtong.led.utils.AsyncUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AsyncController {

    @Autowired
    private AsyncUtils asyncUtils;

    @GetMapping("/async")
    @ResponseBody
    public int async(String name, Integer time){
        asyncUtils.schedule(5);
        return 0;
    }



    @GetMapping("/exception")
    @ResponseBody
    public int exce(){
        System.out.println("准备开始异常。。");
        try {
            asyncUtils.exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
