package com.hengtong.led.lua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lua")
public class LuaController {

    @Autowired
    private LusService lusService;


    @GetMapping(value = "/test")
    public List test(String transactionNo, String type) {
        String hash = String.valueOf((transactionNo + type).hashCode());
        return lusService.access(transactionNo, hash);
    }
}
