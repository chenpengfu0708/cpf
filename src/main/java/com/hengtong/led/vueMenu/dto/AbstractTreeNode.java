package com.hengtong.led.vueMenu.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class AbstractTreeNode {

    private String id;

    private String pid;

    private Collection<AbstractTreeNode> children;

    /**
     * 设置所有子节点
     *
     * @param children
     */
    public void setChildren(Collection children) {
        this.children = children;
    }

}
