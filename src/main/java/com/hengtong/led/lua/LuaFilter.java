package com.hengtong.led.lua;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.CommonErrorCode;
import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.utils.IpAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebFilter(filterName = "luaFilter", urlPatterns = "/lua/*")
public class LuaFilter implements Filter {

    @Value("${lp.key}")
    private String lpKey;

    @Autowired
    private LusService lusService;

    @Autowired
    private IpAddressUtil ipAddressUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> params = servletRequest.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while (params.hasMoreElements()) {
            String p = params.nextElement();
            sb.append(p).append(servletRequest.getParameter(p));
        }
        String data = sb.toString();
        BodyReaderHttpServletRequestWrapper requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        data += requestWrapper.getBody();
        String url = ipAddressUtil.getIP(request);
        String hash = String.valueOf(data.hashCode());
        List lp = lusService.initLp(hash, lpKey, url);
        if (lp.get(0).toString().equals("1")) {
            sendErrorResult(servletResponse, new CommonErrorCode(-1, "相同的请求！"));
            return;
        } else if ((Long) lp.get(1) < 0) {
            sendErrorResult(servletResponse, new CommonErrorCode(-1, "限流中，请稍后！"));
            return;
        } else if (lp.get(2).toString().equals("-1")) {
            sendErrorResult(servletResponse, new CommonErrorCode(-1, "请求过于频繁，请稍后再试！"));
            return;
        }

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void sendErrorResult(ServletResponse resp, CommonErrorCode errorCode) throws IOException {
        CommonResponseDto failResult = new CommonResponseDto();
        failResult.setMessage(errorCode.getMessage());
        failResult.setCode(errorCode.getCode());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(failResult));
    }

}
