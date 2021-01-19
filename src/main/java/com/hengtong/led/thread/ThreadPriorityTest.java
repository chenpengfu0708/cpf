package com.hengtong.led.thread;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fu
 */
public class ThreadPriorityTest {

    private static Object lock = new Object();

    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread a = new Thread(){
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                boolean a = true;
                int num = 0;
                while (a) {
                    try {
                        reentrantLock.lock();
                        System.out.println("a,num = " + num);
                        num++;
                        if (num > 100) {
                            a = false;
                        }
                    } finally {
                        reentrantLock.unlock();
                    }
                }
                System.out.println("a====time = " + (new Date().getTime() - startTime));
            }
        };
        Thread b = new Thread(){
            @Override
            public void run() {
                boolean a = true;
                int num = 0;
                long startTime = System.currentTimeMillis();
                while (a) {
                    try {
                        reentrantLock.lock();
                        System.out.println("b,num = " + num);
                        num++;
                        if (num > 100) {
                            a = false;
                        }
                    } finally {
                        reentrantLock.unlock();
                    }
                }
                System.out.println("b,==time = " + (System.currentTimeMillis() - startTime));
            }
        };
        a.setPriority(1);
        b.setPriority(8);
        a.start();
        b.start();
    }


}
