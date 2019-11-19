package com.hengtong.led.jpaAno.annotation;

import java.lang.annotation.*;

/**
 * 联表查询条件注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLeftJoin {

    /**主表别名*/
    String[] mainTableOtherName();

    /**主表的列名*/
    String[] mainTableColumnName();

    /**关联的表*/
    String[] leftJoinTable();

    /**关联的表别名*/
    String[] leftJoinTableOtherName();

    /**关联的表的列名*/
    String[] leftJoinTableColumnName();

    String leftJoin();

}
