package com.hengtong.led.mybatisPlus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hengtong.led.mybatisPlus.entity.User;
import com.hengtong.led.mybatisPlus.mapper.UserMapper;
import com.hengtong.led.mybatisPlus.service.TestFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestFactoryEmailImpl implements TestFactoryService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> find() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("email", "com");
        return userMapper.selectList(userQueryWrapper);
    }
}
