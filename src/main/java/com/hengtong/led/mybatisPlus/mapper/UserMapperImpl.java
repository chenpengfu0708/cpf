package com.hengtong.led.mybatisPlus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlus.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl {

    @Autowired
    private UserMapper userMapper;

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
        return userMapper.selectList(userQueryWrapper);
    }

}
