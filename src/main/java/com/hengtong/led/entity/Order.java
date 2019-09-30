package com.hengtong.led.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Integer id;
    private String name;
    private String status;
    private Date createTime;
}
