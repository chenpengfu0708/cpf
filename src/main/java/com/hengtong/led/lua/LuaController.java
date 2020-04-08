package com.hengtong.led.lua;

import com.hengtong.led.utils.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lua")
public class LuaController {

    @Autowired
    private LusService lusService;

    @Autowired
    private IpAddressUtil ipAddressUtil;


    @GetMapping(value = "/test")
    public List test(String transactionNo, String type, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ip = " + ipAddressUtil.getIpAddr(request));
        String hash = String.valueOf((transactionNo + type).hashCode());
        return lusService.access(transactionNo, hash);
    }
}
