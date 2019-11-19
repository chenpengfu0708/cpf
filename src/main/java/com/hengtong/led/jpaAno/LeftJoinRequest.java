package com.hengtong.led.jpaAno;


import com.hengtong.led.jpaAno.annotation.*;
import lombok.Data;

/**
 * @author Date : 2019/9/6 10:12
 **/
@Data
@MySelect(select = "u.id as id,user_id as userId,u.name as name,o.status as status,oc.name as orderContent,o" +
        ".create_time as createTime")
@MyFrom(from = "user u")
@MyTable(table = "user", otherName = "u")
@MyLeftJoin(mainTableOtherName = {"u", "o"}, mainTableColumnName = {"id", "id"},
        leftJoinTable = {"order", "order_content"},
        leftJoinTableOtherName = {"o", "oc"}, leftJoinTableColumnName = {"user_id", "order_id"},
        leftJoin = "`order` o on u.id = o.user_id left join `order_content` oc on o.id = oc.order_id")
public class LeftJoinRequest {

    @MyWhere(tableOtherName = "u", columnName = "name", isLike = true, where = "u.name like '%:name%'")
    private String name;

    @MyWhere(tableOtherName = "u", columnName = "id", isGreaterThanEqual = true, where = "u.id >= :id")
    private Integer id;

}
