package com.hengtong.led.lua;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.IOException;
import java.util.List;

/**
 * @author cpf
 * @since 2020-04-09
 */
@Slf4j
@Configuration
public class LuaConfig {

    @Bean(name = "accessScript")
    public RedisScript<List> accessScript() {
        try {
            String script = IoUtil.read(new ClassPathResource("lua/initLp.lua").getInputStream(), "utf-8");
            return RedisScript.of(script, List.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Bean(name = "addLpScript")
    public RedisScript<List> addLpScript() {
        try {
            String script = IoUtil.read(new ClassPathResource("lua/addLp.lua").getInputStream(), "utf-8");
            return RedisScript.of(script, List.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
