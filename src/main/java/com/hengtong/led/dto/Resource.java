package com.hengtong.led.dto;

import lombok.Data;

/**
 * @date: 2019/5/23 14:56
 * @author: rain
 * @description: led
 */
@Data
public class Resource {
    private Integer id;
    private String name;
    private String type;
    private String resourceUrl;
}
