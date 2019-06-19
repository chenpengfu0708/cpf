package com.hengtong.led.utils;


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
