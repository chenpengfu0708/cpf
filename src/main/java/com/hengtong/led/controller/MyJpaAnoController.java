package com.hengtong.led.controller;

import com.hengtong.led.entity.Order;
import com.hengtong.led.jpaAno.LeftJoinRequest;
import com.hengtong.led.jpaAno.TestRepository;
import com.hengtong.led.jpaAno.dto.MyResultDto;
import com.hengtong.led.jpaAno.dto.PageParam;
import com.hengtong.led.jpaAno.service.JPALeftJoin;
import com.hengtong.led.jpaAno.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyJpaAnoController<T> {

    @Autowired
    private JPALeftJoin jpaLeftJoin;

    @Autowired
    private TestRepository testRepository;


    @PostMapping(value = "/myJpaAno")
    public MyResultDto test(@RequestBody LeftJoinRequest request){
        System.out.println(request);
        PageParam pageParam = new PageParam();
        return jpaLeftJoin.test(new Order(), request, pageParam);
    }

}
