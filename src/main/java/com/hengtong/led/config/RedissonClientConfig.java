package com.hengtong.led.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2019/5/29 14:28
 * @author: rain
 * @description: led
 */
@Configuration
public class RedissonClientConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;


    @Bean
    public RedissonClient getRedisson(){

        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }
}
