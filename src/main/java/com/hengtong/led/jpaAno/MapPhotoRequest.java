package com.hengtong.led.jpaAno;


import com.hengtong.led.jpaAno.annotation.MyLeftJoin;
import com.hengtong.led.jpaAno.annotation.MyTable;
import com.hengtong.led.jpaAno.annotation.MyWhere;
import lombok.Data;

/**
 * @author Date : 2019/9/6 10:12
 **/
@Data
@MyTable(table = "user", otherName = "u")
@MyLeftJoin(mainTableOtherName = {"u","o"}, mainTableColumnName = {"id","id"}, leftJoinTable = {"order","order_content"},
        leftJoinTableOtherName = {"o","oc"}, leftJoinTableColumnName = {"user_id","order_id"})
public class MapPhotoRequest {

    @MyWhere(tableOtherName = "u", columnName = "name", isLike = true)
    private String name;

    @MyWhere(tableOtherName = "u", columnName = "id", isGreaterThanEqual = true)
    private Integer id;

}
