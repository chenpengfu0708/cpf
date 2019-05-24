package com.hengtong.led.service;

import com.hengtong.led.utils.AsyncUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2019/5/22 14:48
 * @author: rain
 * @description: led
 */
@Service
public class UserService {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AsyncUtils asyncUtils;


    public void test(){
        teacherService.test();
        System.out.println("UserService.test");
    }

    public void testAsync(){
        System.out.println("testAsync。。。");
        asyncUtils.async();
    }
}
