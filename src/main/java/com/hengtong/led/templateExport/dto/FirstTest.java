package com.hengtong.led.templateExport.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FirstTest {

    private String name;

    private Integer age;

    private Date birthday;

    public FirstTest() {}

    public FirstTest(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}


