package com.hengtong.led.controller;

import com.hengtong.led.sender.FirstSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @date: 2019/5/10 17:16
 * @author: rain
 * @description: led
 */
@Controller
public class RabbitMqController {
    @Autowired
    private FirstSender sender;


    /**
     * 模拟发送mq
     * */
    @GetMapping(value = "/mq")
    @ResponseBody
    public int rabbitMq(String content){
        for (int i=1; i<=500000; i++){
            sender.send(UUID.randomUUID().toString(),content+i);
        }
        return 0;
    }
}
