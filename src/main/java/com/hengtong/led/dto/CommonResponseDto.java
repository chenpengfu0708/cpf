package com.hengtong.led.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponseDto implements Serializable {

    private Integer code;

    private boolean success;

    private String message;

    public CommonResponseDto code(Integer code){
        this.code = code;
        return this;
    }

    public CommonResponseDto success(boolean success){
        this.success = success;
        return this;
    }

    public CommonResponseDto success(String message){
        this.message = message;
        return this;
    }
}
