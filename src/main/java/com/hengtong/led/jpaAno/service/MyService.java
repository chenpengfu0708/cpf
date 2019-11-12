package com.hengtong.led.jpaAno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private MyJpaAnoService myJpaAnoService;

    public void te(){
        myJpaAnoService.test();
    }
}
