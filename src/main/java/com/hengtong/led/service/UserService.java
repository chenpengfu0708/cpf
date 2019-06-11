package com.hengtong.led.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengtong.led.dto.PageResponseDto;
import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper;
import com.hengtong.led.utils.AsyncUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2019/5/22 14:48
 * @author: rain
 * @description: led
 */
@Service
public class UserService {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AsyncUtils asyncUtils;
    @Autowired
    private UserMapper userMapper;


    public void test(){
        teacherService.test();
        System.out.println("UserService.test");
    }

    public void testAsync(){
        System.out.println("testAsync。。。");
        asyncUtils.async();
    }


    /**
     * 分页查询插件
     * */
    public PageResponseDto<User> getUserByPage(Integer pageNum, Integer limit){
        PageHelper.startPage(pageNum, limit);
        List<User> userList = userMapper.getAll();
        PageInfo<User> info = new PageInfo<>(userList);
        PageResponseDto<User> response = new PageResponseDto<>();
        response.setPage(info.getPageNum());
        response.setTotal(info.getTotal());
        response.setSize(info.getSize());
        response.setResult(info.getList());
        return response;
    }
}
