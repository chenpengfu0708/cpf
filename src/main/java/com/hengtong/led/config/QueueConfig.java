package com.hengtong.led.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class QueueConfig {
    @Bean
    public Queue firstQueue() {
        /**
         durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         exclusive  表示该消息队列是否只在当前connection生效,默认是false
         */
        return new Queue("first-queue",true,false,false);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("second-queue",true,false,false);
    }


}
