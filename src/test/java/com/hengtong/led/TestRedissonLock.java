package com.hengtong.led;

import com.hengtong.led.utils.RedisLockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @date: 2019/5/29 14:34
 * @author: rain
 * @description: led
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedissonLock {

    @Autowired
    private RedisLockUtils redisLockUtils;

    @Test
    public void testLock(){
        redisLockUtils.testThreadLock();
    }
}
