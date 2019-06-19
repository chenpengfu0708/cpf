package com.hengtong.led.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class TestRequestDto implements Serializable {
    private Integer id;
    private String name;
}
