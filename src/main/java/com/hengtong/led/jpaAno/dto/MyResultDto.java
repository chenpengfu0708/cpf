package com.hengtong.led.jpaAno.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyResultDto<T> {

    private List<T> data;

    private Long total;
}
