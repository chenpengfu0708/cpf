package com.hengtong.led.dto;

import lombok.Data;


@Data
public class CourseItem {
    private Integer cid;
    private String name;
    private String desc;
    private String category;
    private String courseImg;
    private String add_time;
    private String teacher;
    private Integer student;
    private Integer fav_num;
    private Integer click_num;
    private String point;
}
