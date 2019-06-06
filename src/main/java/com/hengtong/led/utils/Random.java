package com.hengtong.led.utils;


public class Random {

    public static void main(String[] args){
        int[] arr  = {1,2,3,4,5,6,7,8,9};
        int a1,a2,a3;
        java.util.Random random = new java.util.Random();
        //获取0-8的整数
        a1 = random.nextInt(8)%(9);
        do {
            a2 = random.nextInt(8)%(9);
        } while (a2 == a1);
        do {
            a3 = random.nextInt(8)%(9);
        } while (a3 == a1 || a3 == a2);
        System.out.println("三个随机数为：" + arr[a1] + "," + arr[a2] + "," + arr[a3]);
    }
}
