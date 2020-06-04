package com.hengtong.led.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    /**
     * 开启两个定时任务线程
     * @return
     */
//    @Bean
//    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
//        return new ScheduledThreadPoolExecutor(2);
//    }

    //上面的方法也可以，效果一样
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(2));
    }
}
