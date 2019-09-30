package com.hengtong.led.mapper;

import com.hengtong.led.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    @Insert("insert into `order`(id,`name`,`status`,create_time) values(NULL,#{name},#{status},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Order order);



    @Select("select * from `order` where id = #{id}")
    Order getById(@Param("id") Integer id);


    @Update("update `order` set status = #{status} where id = #{id}")
    void updateOrderStatus(@Param("status") String status, @Param("id") Integer id);
}
