package com.hengtong.led.dto;

import lombok.Data;

import java.util.List;

/**
 * @date: 2019/5/15 15:02
 * @author: rain
 * @description: led
 */
@Data
public class TDto<T> {

    List<T> list;
}
