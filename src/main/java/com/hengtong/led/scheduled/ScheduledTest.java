package com.hengtong.led.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTest {

    /*
    @Scheduled：单线程模式的，同一时间内，只允许一个定时任务在执行，
    一个任务在执行的时候，另一个任务会被阻塞掉

    解决办法：配置Scheduled线程池

     */


    @Scheduled(cron = "*/10 * * * * ?")
    public void testOne() {
        log.info("第一个定时任务开始。。。。");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("第一个任务结束。。。");
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void testTwo() {
        log.info("第二个定时任务开始。。。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("第二个任务结束。。。");
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void testThree() {
        log.info("第三个定时任务开始。。。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("第三个任务结束。。。");
    }

}
