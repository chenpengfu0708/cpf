package com.hengtong.led.vueMenu.mapper;

import com.hengtong.led.vueMenu.entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMenuMapper {

    @Select("select * from admin_menu")
    List<AdminMenu> findAll();

}
