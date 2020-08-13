package com.hengtong.led;

import com.hengtong.led.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private RedisUtils redisUtils;


    @Test
    public void test() {
        redisUtils.redisSessionCallBack();
    }

}
