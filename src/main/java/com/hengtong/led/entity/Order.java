package com.hengtong.led.entity;

import com.hengtong.led.jpaAno.annotation.MyIgnore;
import com.hengtong.led.jpaAno.annotation.MyOrder;
import com.hengtong.led.jpaAno.annotation.MyResult;
import com.hengtong.led.jpaAno.enu.OrderByEnum;
import lombok.Data;

import java.util.Date;

@Data
public class Order {

    @MyResult(table = "u", name = "id")
    @MyOrder(order = OrderByEnum.ASC)
    private Integer id;

    private Integer userId;

    @MyResult(table = "u", name = "name")
    private String name;

    @MyResult(table = "o", name = "status")
    private String status;

    @MyResult(table = "oc", name = "name")
    @MyOrder(order = OrderByEnum.ASC)
    private String orderContent;

    @MyResult(table = "o", name = "create_time")
    private Date createTime;

    @MyIgnore
    private String testIgnore;

    @MyIgnore
    private String ignore;
}
