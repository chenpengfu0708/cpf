package com.hengtong.led.controller;

import com.hengtong.led.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestAnnotationController {


    @PostMapping("/testAnnotation")
    @ResponseBody
    public Integer getByPage(@RequestBody User user){
        System.out.println(user);
        return 0;
    }

}
