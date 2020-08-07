package com.hengtong.led.mybatisPlusAndCreateBean.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlusAndCreateBean.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
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
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        userQueryWrapper.gt("create_time", cal.getTime());
        return userMapper.selectList(userQueryWrapper);
    }

}
