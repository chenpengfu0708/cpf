package com.hengtong.led.annotation;

import java.lang.annotation.*;

/**
 * @date: 2019/5/8 14:36
 * @author: rain
 * @description: led
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {

    String get() default "";

}
