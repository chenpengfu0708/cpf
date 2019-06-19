package com.hengtong.led.dto;

import lombok.Data;

import java.util.List;


@Data
public class TDto<T> {

    List<T> list;
}
