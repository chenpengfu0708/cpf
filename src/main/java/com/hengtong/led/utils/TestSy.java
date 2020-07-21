package com.hengtong.led.utils;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

public class TestSy {
    private static int num = 0;

    public static synchronized void add(){
        num++;
    }

    public static void main(String[] args){
        Date birthDay = new Date(-872582400000L);
        int insuredAge = DateUtil.ageOfNow(birthDay);
        System.out.println(insuredAge);
    }
}
