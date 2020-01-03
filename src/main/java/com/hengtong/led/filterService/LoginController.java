package com.hengtong.led.filterService;

import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/login")
public class LoginController {


    @Autowired
    private RedisUtils redisUtil;


    @GetMapping(value = "test")
    public String login() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.setKey("manageuser:token:" + uuid, uuid, 1L, TimeUnit.DAYS);
        return uuid;
    }


}
