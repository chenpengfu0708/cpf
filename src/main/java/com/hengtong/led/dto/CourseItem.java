package com.hengtong.led.dto;

import lombok.Data;

/**
 * @date: 2019/5/23 14:54
 * @author: rain
 * @description: led
 */
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
