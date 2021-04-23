package com.hengtong.led.springsecurity.controller;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.springsecurity.dto.SysUser;
import com.hengtong.led.springsecurity.service.JwtTokenUtil;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 调用登录接口随机生成一个token凭证，再根据这个凭证生成JWT，并把这个凭证放到redis中
     */
    @PostMapping("/login")
    public CommonResponseDto login(@RequestBody SysUser sysUser, HttpServletRequest request){
        String loginCredentials = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtils.setKey(loginCredentials, loginCredentials, 1L, TimeUnit.MINUTES);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginCredentials);
        return new CommonResponseDto().code(0).data(jwtTokenUtil.generateToken(userDetails));
    }

    @PostMapping("haha")
    public String haha(){
        return "123";
    }

    @GetMapping("/ignore/first")
    public void ignore() {
        System.out.println("忽略。。。。。。。。。");
    }


}
