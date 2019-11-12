package com.hengtong.led.jpaAno.annotation;

import com.hengtong.led.jpaAno.enu.OrderByEnum;

import java.lang.annotation.*;

/**
 *  注于返回参数上，用于标识
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyOrder {

    OrderByEnum order() default OrderByEnum.DESC;

    /**排序优先级 0最高 1次之 以此类推*/
    int num() default 0;
}
