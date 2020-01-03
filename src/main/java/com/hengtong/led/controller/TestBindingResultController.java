package com.hengtong.led.controller;

import com.hengtong.led.dto.TestBindingResultRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 测试 Valid 校验字段控制类
 */
@RestController
@Slf4j
public class TestBindingResultController {


    @PostMapping("/testValid")
    @ResponseBody
    public String testValid(@Valid @RequestBody TestBindingResultRequest request, BindingResult bindingResult) {
        log.info("request = {}", request);
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "没毛病";
    }

}
