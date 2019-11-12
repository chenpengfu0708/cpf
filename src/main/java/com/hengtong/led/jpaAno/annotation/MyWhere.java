package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyWhere {

    String tableOtherName();

    String columnName();

    boolean isLike() default false;

    boolean isGreaterThan() default false;

    boolean isGreaterThanEqual() default false;

    boolean isLessThan() default false;

    boolean isLessThanEqual() default false;

}
