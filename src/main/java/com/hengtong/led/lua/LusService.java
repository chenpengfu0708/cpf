package com.hengtong.led.lua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LusService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    RedisScript<List> accessScript;

    /**
     * 调用控制lua 脚本
     * @param transactionNo
     * @param requestBodyHash
     * @return
     */
    public List access(String transactionNo, String requestBodyHash) {
        List<String> keys = new ArrayList<>();
        keys.add(transactionNo);
        keys.add(requestBodyHash);
        log.info("调用lua script 参数:{}",keys);
        return (List) redisTemplate.execute(accessScript, keys);

    }


}
