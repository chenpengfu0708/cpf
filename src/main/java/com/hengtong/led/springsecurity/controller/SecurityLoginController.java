package com.hengtong.led.springsecurity.controller;

import com.hengtong.led.springsecurity.dto.SysUser;
import com.hengtong.led.springsecurity.service.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fu
 */
@RestController
public class SecurityLoginController {

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public String login(@RequestBody SysUser sysUser, HttpServletRequest request){
        UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

    @PostMapping("haha")
    public String haha(){
        System.out.println(".......................");
        return "123";
    }


}
