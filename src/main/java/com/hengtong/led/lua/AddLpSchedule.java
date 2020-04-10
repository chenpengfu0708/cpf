package com.hengtong.led.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cpf
 * @since 2020-04-09
 */
@Component
public class AddLpSchedule {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisScript<List> addLpScript;

    @Value("${lp.key}")
    private String lpKey;


    @Scheduled(cron = "*/10 * * * * ?")
    public void schedule() {
        System.out.println("令牌入桶任务开始，令牌key：" + lpKey);
        List<String> keys = new ArrayList<>();
        keys.add(lpKey);
        System.out.println("令牌入桶完成，目前总数为：" + redisTemplate.execute(addLpScript, keys));
    }

}
