package com.hengtong.led.vueMenu.controller;

import com.hengtong.led.vueMenu.dto.AdminMenuTreeNode;
import com.hengtong.led.vueMenu.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    @GetMapping("/admin/getMenu")
    public List<AdminMenuTreeNode> getMenu(HttpServletRequest request) {
        return adminMenuService.getMenu(request);
    }

}
