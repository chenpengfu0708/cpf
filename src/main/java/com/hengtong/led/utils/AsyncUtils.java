package com.hengtong.led.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class AsyncUtils {


    @Async
    public void async() {
        System.out.println("异步开启...");
        try {
            System.out.println("线程准备沉睡");
            Thread.sleep(10000);
            System.out.println("线程苏醒。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void timer() {
        Timer timer = new Timer();
        System.out.println("star...");
        //5000毫秒后开始执行，每隔2000毫秒执行一次
        timer.schedule(new TimerTask() {
            Long t = System.currentTimeMillis();

            @Override
            public void run() {
                System.out.println("run...");
                System.out.println("time=" + (System.currentTimeMillis() - t));
            }
        }, 5000, 2000);
        System.out.println("end...");
    }

    /**
     * 只会执行一次
     * @param time: 延迟的时间
     */
    public void schedule(Integer time) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        System.out.println("start...");
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        }, time, TimeUnit.SECONDS);
        System.out.println("end...");
    }


    /**
     * 间隔time秒执行一次
     * 如果执行时间大于time，下一次会立刻执行
     * @param time: 延迟的时间
     */
    public void scheduleAtRate(Integer time) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        System.out.println("start...");
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        }, 0L, time, TimeUnit.SECONDS);
        System.out.println("end...");
    }


    public void exception() throws Exception {
        System.out.println("异常抛出。。");
        throw new Exception();
    }
}

