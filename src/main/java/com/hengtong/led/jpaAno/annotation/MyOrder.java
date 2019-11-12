package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

/**
 *  注于返回参数上，用于标识
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyOrder {

    String tableOtherName() default "";

    String columnName() default "";

    boolean isASC() default false;

    boolean isDESC() default true;
}
