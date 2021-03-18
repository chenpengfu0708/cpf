package com.hengtong.led.springsecurity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户标识，赋值用户角色权限，并生成token等信息
 *
 * @author fu
 */
@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        log.info("用户认证凭证：" + user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //需要以 ROLE_ 开头 @Link
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user, authorityList);
    }

}
