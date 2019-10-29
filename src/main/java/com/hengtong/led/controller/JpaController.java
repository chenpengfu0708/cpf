package com.hengtong.led.controller;

import com.hengtong.led.jpa.dto.JpaRequestDto;
import com.hengtong.led.jpa.service.JpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/jpa")
@Slf4j
public class JpaController {

    @Autowired
    private JpaService jpaService;


    @RequestMapping(value = "/doorplateQRCode/search", method = RequestMethod.POST)
    public void test(@RequestBody JpaRequestDto request) {
        jpaService.test(request);
    }


}
