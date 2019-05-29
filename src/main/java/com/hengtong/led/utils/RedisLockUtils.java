package com.hengtong.led.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @date: 2019/5/29 14:22
 * @author: rain
 * @description: led
 */
@Component
public class RedisLockUtils {

    @Autowired
    private RedissonClient redisson;


    public void lock(String key, String num){
        RLock lock = redisson.getLock(key);
        boolean locked = false;
        try{
            locked = lock.tryLock(10, TimeUnit.SECONDS);
            if (locked){
                System.out.println(num + "锁住了。。。");
                System.out.println(num + "准备开始线程沉睡。。");
                Thread.sleep(10);
                System.out.println(num + "线程苏醒。。。");
            } else {
                System.out.println(num + "没锁住。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (locked){
                System.out.println(num + "释放锁");
                lock.unlock();
                System.out.println("");
            }
        }
    }


    public void testThreadLock(){
        String key = "threadLock";
        for (int i=1; i<50; i++){
            String a = "" + i;
            new Thread(){
                @Override
                public void run(){
                    lock("test", this.getName());
                }
            }.start();
        }
    }
}
