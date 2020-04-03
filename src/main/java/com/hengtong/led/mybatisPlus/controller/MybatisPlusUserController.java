package com.hengtong.led.mybatisPlus.controller;


import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlus.entity.User;
import com.hengtong.led.mybatisPlus.service.IUserService;
import com.hengtong.led.mybatisPlus.service.TestFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cpf
 * @since 2020-04-03
 */
@RestController
@RequestMapping("/mybatisPlus/user")
public class MybatisPlusUserController {

    @Autowired
    private IUserService userService;

    @Resource(name = "findEmail")
    private TestFactoryService testFactoryService;

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }


    @ResponseBody
    @GetMapping(value = "/save")
    public Long save(String name, Integer age, String email) {
        return userService.save(name, age, email);
    }

    @ResponseBody
    @PostMapping(value = "/findByCondition")
    public List<User> findByCondition(@RequestBody FindUserRequestDto request) {
        return userService.findByCondition(request);
    }

    @ResponseBody
    @GetMapping(value = "/testFactory")
    public List<User> testFactory() {
        return testFactoryService.find();
    }

}
