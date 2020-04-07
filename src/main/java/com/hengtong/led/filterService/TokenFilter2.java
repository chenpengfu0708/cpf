package com.hengtong.led.filterService;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.CommonErrorCode;
import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(value = -2147483647)
@WebFilter(filterName = "tokenFilter2", urlPatterns = "/filter/*")
public class TokenFilter2 implements Filter {

    @Autowired
    private RedisUtils redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("第二个。。。");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendErrorResult(ServletResponse resp, CommonErrorCode errorCode) throws IOException {
        CommonResponseDto failResult = new CommonResponseDto();
        failResult.setMessage(errorCode.getMessage());
        failResult.setCode(errorCode.getCode());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(failResult));
    }

    @Override
    public void destroy() {

    }
}
