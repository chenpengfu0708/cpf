package com.hengtong.led.dto;

import lombok.Data;

import java.util.List;

/**
 * @date: 2019/6/11 15:13
 * @author: rain
 * @description: led
 */
@Data
public class PageResponseDto<T> {

    private Integer size;
    private Integer page;
    private Long total;
    private List<T> result;
}
