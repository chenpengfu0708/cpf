package com.hengtong.led;

import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper1;
import com.hengtong.led.mybatisPlusAndCreateBean.mapper.UserMapper;
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
    @Autowired
    private UserMapper1 userMapper1;


    @Test
    public void test() {
        redisUtils.redisSessionCallBack();
    }

    @Test
    public void saveUser() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("i = " + i);
            User user = new User();
            user.setName("name" + i);
            user.setAge(20);
            user.setId(28 + i);
            userMapper1.save(user);
        }
    }

}
