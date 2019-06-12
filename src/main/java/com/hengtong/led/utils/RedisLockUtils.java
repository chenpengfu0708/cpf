package com.hengtong.led.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RedisLockUtils {

    @Autowired
    private RedissonClient redisson;
    private static int num1 = 0;


    public void lock(String key, String num){
        RLock lock = redisson.getLock(key);
        boolean locked = false;
        try{
            locked = lock.tryLock(10, TimeUnit.SECONDS);
            if (locked){
                num1++;
                //开始写业务
                System.out.println(num + "锁住了。。。");
                System.out.println(num + "模拟业务耗时开始。。");
                Thread.sleep(10);
                System.out.println(num + "模拟业务耗时结束。。。");
            } else {
                System.out.println(num + "没锁住。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (locked){
                try{
                    System.out.println(num + "释放锁");
                    System.out.println(num1);
                    System.out.println();
                    lock.unlock();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    public void testThreadLock(){
        String key = "threadLock";
        for (int i=1; i<100; i++){
            new Thread(){
                @Override
                public void run(){
                    lock("test", this.getName());
                }
            }.start();
        }
    }
}
