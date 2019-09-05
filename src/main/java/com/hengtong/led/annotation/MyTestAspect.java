package com.hengtong.led.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyTestAspect {


    public void bindAnno(MyTest maxSize){}


//    @Before(value = "bindAnno(maxSize)")
    public void methodBefore(JoinPoint joinPoint, MyTest maxSize) {
        System.out.println("......");
        Object cs = joinPoint.getArgs();
        System.out.println("cs = " + cs);
        if (maxSize.maxSize() > 0) {
            System.out.println("大于0");
        }
    }

//    @After(value = "bindAnno(maxSize)")
    public void after(MyTest maxSize){

    }

}
