package com.hengtong.led.controller;

import com.hengtong.led.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestExceptionController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/testException")
    public void test() {
        userService.test();
    }
}
