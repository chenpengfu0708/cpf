package com.hengtong.led.utils;

import org.springframework.stereotype.Component;

@Component
public class MyStringUtil {


    /**
     * 蛇形转驼峰
     */
    public static String toLowerCase(String str) {
        return String.join("_", str.replaceAll("([A-Z])", ",$1").split(",")).toLowerCase();
    }
}
