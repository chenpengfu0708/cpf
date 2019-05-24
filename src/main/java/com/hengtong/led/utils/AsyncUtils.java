package com.hengtong.led.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2019/5/24 14:08
 * @author: rain
 * @description: led
 */
@Component
public class AsyncUtils {


    @Async
    public void async(){
        System.out.println("异步开启...");
        try {
            System.out.println("线程准备沉睡");
            Thread.sleep(10000);
            System.out.println("线程苏醒。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void timer(){
        Timer timer = new Timer();
        System.out.println("star...");
        //5000毫秒后开始执行，每隔2000毫秒执行一次
        timer.schedule(new TimerTask(){
            Long t = System.currentTimeMillis();
            @Override
            public void run() {
                System.out.println("run...");
                System.out.println("time="+(System.currentTimeMillis() - t));
            }
        },5000, 2000);
        System.out.println("end...");
    }

    /**
     * @param time: 延迟的时间
     * */
    public void schedule(Integer time){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        System.out.println("start...");
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        },time, TimeUnit.SECONDS);
        System.out.println("end...");
    }
}
