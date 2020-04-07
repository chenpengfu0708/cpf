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

@Order(value = -2147483648)
@WebFilter(filterName = "tokenFilter", urlPatterns = "/filter/*")
public class TokenFilter implements Filter {

    @Autowired
    private RedisUtils redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest htRequest = (HttpServletRequest) servletRequest;
        System.out.println("第一个。。。");
        String token = htRequest.getHeader("ManageUserAccessToken");
        if (StringUtils.isNotBlank(token)) {
            String key = "manageuser:token:" + token;
            String userId = redisUtil.getKey(key);
            if (StringUtils.isBlank(userId)) {
                sendErrorResult(servletResponse, CommonErrorCode.USER_MUST_LOGNIN);
                return;
            }
        } else {
            sendErrorResult(servletResponse, CommonErrorCode.USER_MUST_LOGNIN);
            return;
        }
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
