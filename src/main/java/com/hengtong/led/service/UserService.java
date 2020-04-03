package com.hengtong.led.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengtong.led.dto.PageResponseDto;
import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserMapper1 userMapper1;

    /**
     * 分页查询插件
     * */
    public PageResponseDto<User> getUserByPage(Integer pageNum, Integer limit){
        PageHelper.startPage(pageNum, limit);
        List<User> userList = userMapper1.getAll();
        PageInfo<User> info = new PageInfo<>(userList);
        PageResponseDto<User> response = new PageResponseDto<>();
        response.setPage(info.getPageNum());
        response.setTotal(info.getTotal());
        response.setSize(info.getSize());
        response.setResult(info.getList());
        return response;
    }
}
