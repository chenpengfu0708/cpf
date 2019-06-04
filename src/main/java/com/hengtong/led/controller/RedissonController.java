package com.hengtong.led.controller;

import com.hengtong.led.utils.RedisLockUtils;
import com.hengtong.led.utils.RedisUtils;
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
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/redisson")
    @ResponseBody
    public int redisson(){
        redisLockUtils.testThreadLock();
        return 0;
    }


    @GetMapping("/redis_count")
    @ResponseBody
    public int redisCount(Long uid){
        String key = "testCount";
        System.out.println("去重统计总数为：" + redisUtils.bitCount(key));
        redisUtils.setBitmap(key, uid);
        System.out.println("去重统计总数为：" + redisUtils.bitCount(key));
        return 0;
    }


}
