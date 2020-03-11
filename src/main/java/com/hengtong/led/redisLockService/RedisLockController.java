package com.hengtong.led.redisLockService;

import com.hengtong.led.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class RedisLockController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisLockService redisLockService;


    @GetMapping(value = "/redisLockTest")
    public Integer redisLockTest() {
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(redisLockByTime(this.getName(), "redisLockByTime", 5D));
                }
            }.start();
        }
        return 0;
    }


    /**
     * 轮询争抢锁操作
     *
     * @param name : 线程名称（可去掉）
     * @param key  : 需要加锁的key
     * @param time : 争抢总时间
     */
    public String redisLockByTime(String name, String key, Double time) {
        boolean locked = redisLockService.tryLock(key, time);
        if (locked) {
            System.out.println(name + "抢到了。。。");
            try {
                //模拟业务耗时 400 毫秒
                TimeUnit.MILLISECONDS.sleep(400);
                redisLockService.unLock(key);
                System.out.println(name + "释放锁");
            } catch (InterruptedException e) {
                log.error("线程沉睡异常" + e);
            }
            return name + "抢到了";
        } else {
            System.out.println(name + "没抢到...");
            return name + "没抢到";
        }
    }


    /**
     *
     */
    public void tryLock(String name) {
        String key = "redisKey";
        boolean locked = redisUtils.tryLock(key, 5L, TimeUnit.SECONDS);
        if (locked) {
            try {
                System.out.println(name + "锁住了。。。开始线程沉睡");
                TimeUnit.SECONDS.sleep(2);
                redisUtils.unLock(key);
                System.out.println(name + "释放锁。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(name + "没抢到，溜了。。。");
        }
    }

}
