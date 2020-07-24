package com.hengtong.led.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponseDto<T> implements Serializable {

    private Integer code;

    private boolean success;

    private String message;

    private T data;

    private Integer finish;

    public CommonResponseDto code(Integer code){
        this.code = code;
        return this;
    }

    public CommonResponseDto success(boolean success){
        this.success = success;
        return this;
    }

    public CommonResponseDto message(String message){
        this.message = message;
        return this;
    }

    public CommonResponseDto data(T data){
        this.data = data;
        return this;
    }

    public CommonResponseDto finish(Integer finish){
        this.finish = finish;
        return this;
    }
}
