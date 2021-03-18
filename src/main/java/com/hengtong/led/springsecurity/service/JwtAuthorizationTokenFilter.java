package com.hengtong.led.springsecurity.service;

import com.hengtong.led.utils.RedisUtils;
import com.hengtong.led.utils.SpringUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * filter不能给Spring容器托管，不然WebSecurity中ignoring的url会失效，继续走过滤器
 *
 * @author fu
 */
//@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private String tokenHeader = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        UserDetailsService userDetailsService = SpringUtil.getBean(JwtUserDetailsService.class);
        JwtTokenUtil jwtTokenUtil = SpringUtil.getBean(JwtTokenUtil.class);
        RedisUtils redisUtils = SpringUtil.getBean(RedisUtils.class);
        String authToken = request.getHeader(this.tokenHeader);
        String username = null;
        if (authToken != null && !"".equals(authToken)) {
            try {
                username = jwtTokenUtil.getUserNameFromToken(authToken);
            } catch (ExpiredJwtException e) {
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                System.out.println("认证成功：" + username);
                redisUtils.refreshKey(username, 1L, TimeUnit.MINUTES);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        chain.doFilter(request, response);
    }
}
