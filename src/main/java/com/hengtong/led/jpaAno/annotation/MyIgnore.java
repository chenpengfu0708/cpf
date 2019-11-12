package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

/**
 * 注于响应类参数上，标明数据库返回忽略该参数
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIgnore {
}
