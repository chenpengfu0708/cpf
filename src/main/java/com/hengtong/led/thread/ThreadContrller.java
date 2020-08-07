package com.hengtong.led.thread;

import com.hengtong.led.mybatisPlusAndCreateBean.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadContrller {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        Map<String, Future<User>> results = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String url = "url" + i;
            results.put("" + i,exec.submit(new TaskCallable(i,url, "a", User.class)));
        }
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i <5 ; i++) {
            try {
                User response = results.get("" + i).get();
                System.out.println(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("总用时：" + (endTime - startTime));

    }
}
