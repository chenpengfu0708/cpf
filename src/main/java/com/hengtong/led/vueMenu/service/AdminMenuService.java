package com.hengtong.led.vueMenu.service;

import com.hengtong.led.vueMenu.dto.AdminMenuTreeNode;
import com.hengtong.led.vueMenu.mapper.AdminMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    public List<AdminMenuTreeNode> getMenu(HttpServletRequest request) {
        return null;
    }

}
