package com.hengtong.led;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengtong.led.dto.PageResponseDto;
import com.hengtong.led.entity.User;
import com.hengtong.led.mapper.UserMapper1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPageInfoTest {

    @Autowired
    private UserMapper1 userMapper1;


    @Test
    public void pageTest(){
        PageHelper.startPage(1, 10);
        List<User> userList = userMapper1.getAll();
        PageInfo<User> info = new PageInfo<>(userList);
        PageResponseDto<User> response = new PageResponseDto<>();
        response.setPage(info.getPageNum());
        response.setTotal(info.getTotal());
        response.setSize(info.getSize());
        response.setResult(info.getList());
        System.out.println(response.getResult().get(0));
    }

}
