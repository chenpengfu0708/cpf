package com.hengtong.led.controller;

import com.hengtong.led.utils.rocketMq.RocketmqProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RocketMqController {
    @Autowired
    private RocketmqProducerService rocketmqProducerService;

    @GetMapping("/send")
    @ResponseBody
    public boolean send(String msg){
        return rocketmqProducerService.sendMsg(msg);
    }
}
