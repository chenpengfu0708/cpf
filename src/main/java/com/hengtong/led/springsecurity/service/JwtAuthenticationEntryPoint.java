package com.hengtong.led.springsecurity.service;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.dto.CommonResponseDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fu
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        System.out.println("JwtAuthenticationEntryPoint:"+authException.getMessage());
        CommonResponseDto commonResponseDto = new CommonResponseDto().code(HttpServletResponse.SC_UNAUTHORIZED).message("认证信息为空!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(commonResponseDto));
    }
}
