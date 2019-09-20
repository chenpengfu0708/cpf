package com.hengtong.led;

import com.alibaba.fastjson.JSONObject;
import com.hengtong.led.dto.TestJsonDto;
import com.hengtong.led.entity.FanShe;
import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper;
import com.hengtong.led.utils.RedisLockUtils;
import com.hengtong.led.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedissonLock {

    @Autowired
    private RedisLockUtils redisLockUtils;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserMapper userMapper;

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


    @Test
    public void testAnnotation(){
        User user = new User();
        user.setId(1);
        user.setName("name");
        System.out.println(user);
    }


    @Test
    public void filedTest() {
        List<FanShe> fanSheList = new ArrayList<>();
        fanSheList.add(new FanShe("name", "姓名"));
        fanSheList.add(new FanShe("sex", "性别"));
        fanSheList.add(new FanShe("age", 20));
        TestJsonDto dto = new TestJsonDto();
        String name = "";

        System.out.println(userMapper.update(1, 1));

        System.out.println("name" + name.getClass().getCanonicalName());
        for (FanShe fanShe : fanSheList) {
            for (Field field : dto.getClass().getDeclaredFields()) {
                if (field.getName().equals(fanShe.getName())) {
                    System.out.println("filed = " + field);
                    System.out.println("fanShe = " + fanShe);
                    try {
                        field.setAccessible(true);
                        field.set(dto, fanShe.getValue());
                        System.out.println("dto = " + dto);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
