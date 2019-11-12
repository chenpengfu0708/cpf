package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

/**
 * 响应类参数注解
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyResult {

    String table() default "";

    String name();
}
