package com.hengtong.led.mybatisPlusAndCreateBean.controller;


import com.hengtong.led.CommonErrorCode;
import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.dto.HandlerDto;
import com.hengtong.led.exception.BusinessRuntimeException;
import com.hengtong.led.mybatisPlusAndCreateBean.entity.User;
import com.hengtong.led.mybatisPlusAndCreateBean.factory.HandlerBeanFactory;
import com.hengtong.led.mybatisPlusAndCreateBean.factory.HandlerFactory;
import com.hengtong.led.mybatisPlusAndCreateBean.service.IUserService;
import com.hengtong.led.mybatisPlusAndCreateBean.service.TestFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cpf
 * @since 2020-04-03
 */
@Slf4j
@RestController
@RequestMapping("/mybatisPlus/user")
public class MybatisPlusUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private HandlerFactory handlerFactory;
    @Autowired
    private HandlerBeanFactory handlerBeanFactory;

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
    public List<User> testFactory(String type) {
        HandlerDto handlerDto = handlerFactory.getFactory().get(type);
        if (handlerDto == null) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVICE_TYPE_ERROR);
        }
        try {
            Optional<TestFactoryService> service = handlerBeanFactory.getHandler(handlerDto.getClassName(),
                    Class.forName(handlerDto.getClassName()));
            return service.get().find();
        } catch (Exception e) {
            log.error("业务辨别失败：" + e);
        }
        return null;
    }


    /**
     * 加载servlet时执行一次
     */
    @PostConstruct
    public void te() {
        List<User> list = userService.findAll();
        list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
//        System.out.println("加载servlet时执行一次: "+userService.findAll());
    }

}
