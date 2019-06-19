package com.hengtong.led.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TestScheduled {

    @Scheduled(cron = " */5 * * * * ?")
    public void test(){
//        System.out.println("哈哈");
    }
}
