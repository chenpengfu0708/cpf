package com.hengtong.led.lua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;
import cn.hutool.core.io.IoUtil;

import java.io.IOException;
import java.util.List;

@Slf4j
@Configuration
public class LuaConfig {

    @Bean(name = "accessScript")
    public RedisScript<List> accessScript() {
        try {
            String script = IoUtil.read(new ClassPathResource("lua/access.lua").getInputStream(), "utf-8");
            RedisScript of = RedisScript.of(script, List.class);
            log.info("lua/access.lua 的 sha1 值为 :{}", of.getSha1());
            return of;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
