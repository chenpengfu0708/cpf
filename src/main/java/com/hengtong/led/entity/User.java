package com.hengtong.led.entity;

import com.hengtong.led.annotation.MyTest;
import lombok.Data;
@Data
public class User {
    private Integer id;
    private String name;

    @MyTest(maxSize = 10, message = "年龄太大")
    private Integer age;

}
