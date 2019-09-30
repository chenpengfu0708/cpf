package com.hengtong.led.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5Util {


    /**
     * md5加密
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
