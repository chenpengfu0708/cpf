package com.hengtong.led.mybatisPlus.service;

import com.hengtong.led.dto.FindUserRequestDto;
import com.hengtong.led.mybatisPlus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cpf
 * @since 2020-04-03
 */
public interface IUserService extends IService<User> {

    List<User> findAll();

    Long save(String name, Integer age, String email);

    List<User> findByCondition(FindUserRequestDto request);

}
