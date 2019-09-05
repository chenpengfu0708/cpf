package com.hengtong.led.entity;

import lombok.Data;

@Data
public class FanShe {

    private  String name;

    private Object value;

    public FanShe(){}

    public FanShe(String name, Object value){
        this.name = name;
        this.value = value;
    }
}
