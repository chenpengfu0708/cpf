package com.hengtong.led.jpa.service;

import com.hengtong.led.jpa.dto.JpaRequestDto;
import com.hengtong.led.jpa.repository.JpaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaService {

    @Autowired
    private JpaEntityRepository jpaEntityRepository;

    public void test(JpaRequestDto request){
        System.out.println(jpaEntityRepository.findByCondition(request));
        System.out.println(jpaEntityRepository.countByCondition(request));
    }

}
