package com.hengtong.led.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @date: 2019/6/17 16:45
 * @author: rain
 * @description: led
 */
@Data
public class TestRequestDto implements Serializable {
    private Integer id;
    private String name;
}
