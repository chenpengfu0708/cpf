package com.hengtong.led.heibaidiedai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HbddController {

    @Autowired
    private HbddMain hbddMain;


    @GetMapping("/hbdd")
    public Map<String, String> hbdd(Integer a, Integer b, String token) {
        long startTime = System.currentTimeMillis();
        Map<String, String> map = hbddMain.sout(a, b, token);
        System.out.println();
        System.out.println("用时：" + (System.currentTimeMillis() - startTime));
        return map;
    }

}
