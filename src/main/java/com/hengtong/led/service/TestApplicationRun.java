package com.hengtong.led.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;


@Order(1)
@Component
public class TestApplicationRun implements ApplicationRunner {
    private static final ThreadPoolExecutor cousmer = new ScheduledThreadPoolExecutor(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TestRun run = new TestRun();
        cousmer.execute(run);

    }
}
