package com.hengtong.led.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@ConditionalOnProperty(prefix = "myScheduling", name = "enable", havingValue = "true", matchIfMissing = true)
@Component
public class TestScheduled implements ApplicationListener<WebServerInitializedEvent> {

    private int post;

    @Scheduled(cron = " */5 * * * * ?")
    public void test() {
        System.out.println("哈哈" + post);

    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.post = webServerInitializedEvent.getWebServer().getPort();
    }
}
