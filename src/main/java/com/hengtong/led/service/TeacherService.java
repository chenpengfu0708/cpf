package com.hengtong.led.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2019/5/22 14:48
 * @author: rain
 * @description: led
 */
@Service
public class TeacherService {

    @Autowired
    private UserService userService;

    public void test(){
        System.out.println("TeacherService.test");
    }
}
