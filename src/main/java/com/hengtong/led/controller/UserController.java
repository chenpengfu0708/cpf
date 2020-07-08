package com.hengtong.led.controller;


import com.hengtong.led.dto.PageResponseDto;
import com.hengtong.led.dto.TDto;
import com.hengtong.led.entity.User;
import com.hengtong.led.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/testCount")
    @ResponseBody
    public void testCount() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //补偿的，reids的key，
                        //void t（）
                        System.out.println("子线程开启。。。。");
                    } catch (Exception e) {
                        log.error("子线程异常：" + e);
                    } finally {
                        latch.countDown();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            latch.await();
            //去查redis有没有命中
            System.out.println("主线程往下走");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/page")
    @ResponseBody
    public PageResponseDto<User> getByPage(Integer page, Integer limit) {
        return userService.getUserByPage(page, limit);
    }


    @GetMapping("/t")
    @ResponseBody
    public TDto t() {
        TDto dto = new TDto();
        HashMap<String, String> result = new HashMap<>();
        result.put("url", "12233455");
        dto.setResult(result);

        HashMap<String, String> a = (HashMap) dto.getResult();
        System.out.println(a.get("url"));
        return dto;
    }

    @GetMapping(value = {"/one1", "/two", "/three"})
    @ResponseBody
    public void paymentGateway() {
        System.out.println(".........");
    }
}
