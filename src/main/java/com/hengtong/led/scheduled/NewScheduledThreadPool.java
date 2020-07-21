package com.hengtong.led.scheduled;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class NewScheduledThreadPool {


    public static void main(String[] args) {
        ExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(3);
    }
}


