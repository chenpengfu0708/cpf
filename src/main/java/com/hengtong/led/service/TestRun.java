package com.hengtong.led.service;

/**
 * @date: 2019/4/29 16:58
 * @author: rain
 * @description: led
 */
public class TestRun implements Runnable {

    @Override
    public void run() {
        while (true){
//            System.out.println("I am here。。。。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public TestRun(){
        super();
    }


}
