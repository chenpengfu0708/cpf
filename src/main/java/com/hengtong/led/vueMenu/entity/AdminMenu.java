package com.hengtong.led.vueMenu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理后台菜单表
 * </p>
 *
 * @author Liang Wenxu
 * @since 2018-12-14
 */
@Data
public class AdminMenu {

    private String id;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime dateCreated;

    // 删除时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deleteDate;

    // 逻辑删除标志，1为已删除，0为未删除
    private Integer deleteFlag = 0;

    // 最后更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastUpdated;

    // 数据版本号，用于乐观锁，insert后为1，update后自增
    private Integer version;

    // 菜单代码
    private String code;

    // 图标URL（小）
    private String icon;

    // 图标图片URL（大）
    private String iconLarge;

    // 菜单名称
    private String name;

    // 目标（_blank等）
    private String target;

    // 菜单链接
    private String url;

    // 权限ID
    private String permId;

    // 上级菜单ID
    private String parentId;

    // 显示顺序，越小越靠前
    private Integer showIdx;

}
