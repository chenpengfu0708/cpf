package com.hengtong.led.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class TalkUtils {

    public static void main(String[] args){
        System.out.println("智能聊天开始。。。");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String quetion = scanner.next();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String daan = say(quetion);
            System.out.println(daan);
        }
    }

    public static String say(String quetion){
        switch (quetion) {
            case "饿？":
                return "你说呢";
            case "哈哈":
                return "笑个屁";
            case "想吃啥":
                return "青瓜炒肉";
            case "没有":
                return "那还有什么好说的";
            case "喝的呢":
                return "鸡汤吧";
            case "叶坦你认识么":
                return "噢，那个傻逼啊";
            case "傻逼？":
                return "不然呢";
            default:
                return "不知道你想说啥！";
        }
    }
}
