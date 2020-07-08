package com.hengtong.led.controller;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TestBFController {

    private static boolean lock = false;

    private static volatile int num = 0;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/bfOne")
    public CommonResponseDto bfOne(@RequestBody FindUserRequestDto request) {
        log.info("bfOne,request = " + request);
        return new CommonResponseDto().code(0).success(true).message("bfOne");
    }

    @PostMapping("/bfTwo")
    public CommonResponseDto bfTwo(@RequestBody FindUserRequestDto request) {
        log.info("bfTwo,request = " + request);
        return new CommonResponseDto().code(0).success(true).message("bfTwo");
    }

    @PostMapping("/bfThree")
    public CommonResponseDto bfThree(@RequestBody FindUserRequestDto request) {
        log.info("bfThree,request = " + request);
        return new CommonResponseDto().code(0).success(true).message("bfThree");
    }


    public void te() {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        while (true) {
            CountDownLatch latch = new CountDownLatch(3);
            for (int i = 0; i < 3; i++) {
                exec.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (redisUtils.tryLock("runKey", 100L, TimeUnit.SECONDS)) {
                                log.info("========抢到了锁======");
                                while (true) {
                                    try {
                                        log.info("开始给num+1，num = " + num);
                                        Thread.sleep(100);
                                        num++;
                                        if (num%10 == 0) {
                                            log.info("num%10=0，我溜了");
                                            num = 0;
                                            return;
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } finally {
                                        redisUtils.remove("redis:lock:runKey");
                                    }
                                }
                            } else {
                                log.info("没抢到。。。。。。。。");
                            }
                        } catch (Exception e) {
                            log.info("有异常........" + e);
                            e.printStackTrace();
                        }finally {
                            log.info("执行完一个，计数器减一");
                            latch.countDown();
                        }
                    }
                });
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
