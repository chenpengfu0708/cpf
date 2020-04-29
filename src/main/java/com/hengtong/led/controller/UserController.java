package com.hengtong.led.controller;


import com.hengtong.led.dto.PageResponseDto;
import com.hengtong.led.dto.TDto;
import com.hengtong.led.entity.User;
import com.hengtong.led.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/page")
    @ResponseBody
    public PageResponseDto<User> getByPage(Integer page, Integer limit) {
        return userService.getUserByPage(page, limit);
    }


    @GetMapping("/t")
    @ResponseBody
    public TDto t() {
        TDto dto = new TDto();
        HashMap<String, String> result = new HashMap<>();
        result.put("url", "12233455");
        dto.setResult(result);

        HashMap<String, String> a = (HashMap) dto.getResult();
        System.out.println(a.get("url"));
        return dto;
    }
}
