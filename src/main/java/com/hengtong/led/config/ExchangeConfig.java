package com.hengtong.led.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExchangeConfig {

    @Bean
    public DirectExchange directExchange(){
//        DirectExchange directExchange = new DirectExchange(RabbitMqConfig.EXCHANGE,true,false);
//        return directExchange;
        return null;
    }


}
