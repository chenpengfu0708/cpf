package com.hengtong.led.redisLockService;

import com.hengtong.led.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisLockService {

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 轮询加锁操作 0.01秒发起一次抢锁操作
     *
     * @param key  : 锁的key
     * @param time : 争抢锁的时间-秒
     */
    public Boolean tryLock(String key, Double time) {
        boolean locked = false;
        if (time > 0) {
            locked = redisUtils.tryLock(key, 5L, TimeUnit.SECONDS);
            if (locked) {
                return locked;
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                    locked = tryLock(key, time - 0.01);
                } catch (InterruptedException e) {
                    log.error("线程沉睡异常" + e);
                }
            }
        }
        return locked;
    }


    public void unLock(String key) {
        redisUtils.unLock(key);
    }


}
