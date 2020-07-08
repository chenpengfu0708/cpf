package com.hengtong.led.thread.cyclicBarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * 作用：等待线程全部集合完毕，才一起去执行相应的函数
 * 与CountDownLatch相似，但比它强大在于，可以循环利用，还是在执行之后指定另外一个函数
 * */
@Slf4j
public class TestCyclicBarrier {


    /**
     * 士兵类
     */
    public static class Soldier implements Runnable {

        private String soldierName;

        private final CyclicBarrier cyclicBarrier;

        Soldier(String soldierName, CyclicBarrier cyclicBarrier){
            this.soldierName = soldierName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵集合完毕
                log.info("等待所偶遇士兵集合。。。。");
                cyclicBarrier.await();
                log.info("士兵已经集合完毕。。。。");
                doWork();
                //等待所有士兵完成任务
                cyclicBarrier.await();
                log.info("所有士兵已经完成任务。。。。");
            } catch (InterruptedException | BrokenBarrierException e) {

            }
        }

        public void doWork() {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(soldierName + ":完成任务");
        }
    }


    /**
     * 后续需要执行的函数
     */
    public static class BarrierRun implements Runnable {

        private boolean flag;

        private int n;

        BarrierRun(boolean flag, int n) {
            this.flag = false;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                log.info("司令：士兵" + n + "完成任务");
            } else {
                log.info("司令：士兵" + n + "集合完成");
                flag = true;
            }
        }
    }


    /**
     * main方法
     */
    public static void main(String[] args) {
        final int n = 10;
        Thread[] allSoldier = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrierRun(flag, n));
        log.info("集合啦。。。。。");
        for (int i = 0; i < n; i++) {
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
        }
    }

}
