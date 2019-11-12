package com.hengtong.led.jpaAno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Method;

@Aspect
@Component
public class MyAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Around(value = "@annotation(com.hengtong.led.jpaAno.MyAno)")
    public String excute(ProceedingJoinPoint pjp){

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();

        MyAno myAno = method.getAnnotation(MyAno.class);
        for (int i=0; i<myAno.param().length; i++) {
            System.out.println("param = " + myAno.param()[i]);
            System.out.println("value = " + myAno.value()[i]);
            System.out.println();
        }


        return "success";
    }

}
