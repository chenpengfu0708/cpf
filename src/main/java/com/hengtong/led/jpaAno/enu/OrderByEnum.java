package com.hengtong.led.jpaAno.enu;


public enum OrderByEnum {

    ASC("升序"),
    DESC("降序");

    private String name;

    public String getName() {
        return name;
    }

    OrderByEnum(String name){
        this.name = name;
    }
}
