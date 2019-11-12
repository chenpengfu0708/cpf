package com.hengtong.led.jpaAno.service;

import com.hengtong.led.jpaAno.MyAno;
import org.springframework.stereotype.Component;

@Component
public class MyJpaAnoService {

    @MyAno(param = {"参数1","参数2","参数3"}, value = {"值1","值2","值3"})
    String test(){
        return "";
    }
}
