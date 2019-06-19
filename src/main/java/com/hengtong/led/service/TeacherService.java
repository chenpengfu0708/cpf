package com.hengtong.led.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherService {

    @Autowired
    private UserService userService;

    public void test(){
        System.out.println("TeacherService.test");
    }
}
