package com.hengtong.led.mapper;

import com.hengtong.led.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into user(id,name,age) values(NULL,#{name},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(User user);

    @Select("select * from user")
    List<User> getAll();


    @Select({
            "<script>" +
                    "select * from user where id in " +
                    "<foreach item = 'item' index = 'index' collection = 'ids' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>"+
            "</script>"})
    List<User> getByIds(@Param("ids") List<Integer> ids);



    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Integer id);


    @Select("select * from user where name = #{name}")
    List<User> getByName(@Param("name") String name);


    @Select("select * from user where name = #{name} and age >= #{age}")
    List<User> getByMap(Map map);

    @Update("update user set age = #{age} where id = #{id}")
    Integer update(@Param("age") Integer age, @Param("id") Integer id);

}
