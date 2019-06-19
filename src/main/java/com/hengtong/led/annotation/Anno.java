package com.hengtong.led.annotation;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {

    String get() default "";

}
