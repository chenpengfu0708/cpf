package com.hengtong.led.controller;

import com.hengtong.led.utils.RedisLockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date: 2019/5/29 17:23
 * @author: rain
 * @description: led
 */
@Controller
public class RedissonController {
    @Autowired
    private RedisLockUtils redisLockUtils;

    @GetMapping("/redisson")
    @ResponseBody
    public int redisson(){
        redisLockUtils.testThreadLock();
        return 0;
    }
}
