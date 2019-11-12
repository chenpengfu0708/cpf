package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTable {

    String table();

    String otherName();
}
