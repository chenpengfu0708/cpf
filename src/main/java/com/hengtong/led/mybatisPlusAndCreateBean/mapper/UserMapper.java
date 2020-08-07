package com.hengtong.led.mybatisPlusAndCreateBean.mapper;

import com.hengtong.led.mybatisPlusAndCreateBean.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cpf
 * @since 2020-04-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
