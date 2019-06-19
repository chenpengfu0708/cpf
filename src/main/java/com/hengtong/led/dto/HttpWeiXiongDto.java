package com.hengtong.led.dto;

import lombok.Data;

import java.util.List;


@Data
public class HttpWeiXiongDto {

    private Integer code;
    private String msg;
    private CourseItem course_item;
    private List<Resource> resourse_list;

    public HttpWeiXiongDto code(Integer code){
        this.code = code;
        return this;
    }

    public HttpWeiXiongDto msg(String msg){
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "HttpWeiXiongDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", course_item=" + course_item +
                ", resourse_list=" + resourse_list +
                '}';
    }
}
