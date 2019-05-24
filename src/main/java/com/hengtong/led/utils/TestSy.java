package com.hengtong.led.utils;

/**
 * @date: 2019/5/20 17:55
 * @author: rain
 * @description: led
 */
public class TestSy {
    private static int num = 0;

    public static synchronized void add(){
        num++;
    }

    public static void main(String[] args){
        for (int i = 0; i<50; i++){
            new Thread(){
            }.start();
            TestSy.add();
        }

    }
}
