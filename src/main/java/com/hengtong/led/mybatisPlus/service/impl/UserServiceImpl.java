package com.hengtong.led.mybatisPlus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlus.entity.User;
import com.hengtong.led.mybatisPlus.mapper.UserMapper;
import com.hengtong.led.mybatisPlus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
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
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(request.getName())) {
            userQueryWrapper.like("name", request.getName());
        }
        if (request.getId() != null) {
            userQueryWrapper.eq("id", request.getId());
        }
        if (StringUtils.isNotEmpty(request.getEmail())) {
            userQueryWrapper.like("email", request.getEmail());
        }
        return this.baseMapper.selectList(userQueryWrapper);
    }
}
