package com.hengtong.led.mybatisPlus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlus.entity.User;
import com.hengtong.led.mybatisPlus.mapper.UserMapper;
import com.hengtong.led.mybatisPlus.mapper.UserMapperImpl;
import com.hengtong.led.mybatisPlus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cpf
 * @since 2020-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapperImpl userMapperImpl;

    @Override
    public List<User> findAll() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public Long save(String name, Integer age, String email) {
        User use = new User(name, age, email);
        this.baseMapper.insert(use);
        return use.getId();
    }

    @Override
    public List<User> findByCondition(FindUserRequestDto request) {
        return userMapperImpl.findByCondition(request);
    }
}
