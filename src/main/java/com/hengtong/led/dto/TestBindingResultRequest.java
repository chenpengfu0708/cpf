package com.hengtong.led.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class TestBindingResultRequest {

    @NotBlank(message = "first不允许为空")
    @Length(max = 10, message = "first长度超出限制")
    private String first;

    @NotBlank(message = "second不允许为空")
    @Length(max = 20, message = "second长度超出限制")
    private String second;

}
