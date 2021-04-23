package com.hengtong.led.utils;

/**
 * @author fu
 */
public class MyTest {

    public static void main(String[] args) {
        int a = 0;
        while (true) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a++;
            System.out.println("11111:" + a);

            if (a > 5) {
                break;
            }
        }
        System.out.println("结束");
    }
}
