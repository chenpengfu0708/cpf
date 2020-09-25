package com.hengtong.led.vueMenu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理后台菜单（树形结构节点）
 * </p>
 *
 * @author Liang Wenxu
 * @since 2018-12-14
 */
@Data
@EqualsAndHashCode
public class AdminMenuTreeNode extends AbstractTreeNode {

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

    // 显示顺序，越小越靠前
    private Integer showIdx;

    /**
     * 获取节点值
     *
     * @return
     */
    public Object getValue() {
        return this.getCode();
    }

    /**
     * 设置节点值
     *
     * @param value
     */
    public void setValue(Object value) {

    }
}
