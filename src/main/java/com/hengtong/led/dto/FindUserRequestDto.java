package com.hengtong.led.dto;

import lombok.Data;

@Data
public class FindUserRequestDto {
    private String name;
    private Long id;
    private String email;
}
