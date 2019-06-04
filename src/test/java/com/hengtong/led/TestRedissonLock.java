package com.hengtong.led;

import com.alibaba.fastjson.JSONObject;
import com.hengtong.led.dto.TestJsonDto;
import com.hengtong.led.utils.RedisLockUtils;
import com.hengtong.led.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedissonLock {

    @Autowired
    private RedisLockUtils redisLockUtils;
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testLock(){
        redisLockUtils.testThreadLock();
    }


    @Test
    public void testJson(){
        JSONObject object = new JSONObject();
        object.put("name", "测试");
        object.put("sex", "男");
        object.put("age", 24);
        System.out.println("object="+object);
        TestJsonDto jsonDto = new TestJsonDto();
        System.out.println(jsonDto);
        jsonDto = JSONObject.parseObject(object.toString(), TestJsonDto.class);
        System.out.println(jsonDto);

    }


}
