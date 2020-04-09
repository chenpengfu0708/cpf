package com.hengtong.led.lua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cpf
 * @since 2020-04-09
 */
@Slf4j
@Service
public class LusService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    RedisScript<List> accessScript;

    /**
     * 调用控制lua 脚本
     * @return
     */
    public List initLp(String dataHash, String lpKey, String ip) {
        List<String> keys = new ArrayList<>();
        keys.add(dataHash);
        keys.add(lpKey);
        keys.add(ip);
        keys.add(String.valueOf(System.currentTimeMillis()));
        return (List) redisTemplate.execute(accessScript, keys);

    }


}
