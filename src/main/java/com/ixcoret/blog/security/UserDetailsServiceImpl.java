package com.ixcoret.blog.security;

import com.ixcoret.blog.entity.SysUser;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/10/6 18:16
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new BusinessException(ResultCodeEnum.PARAMS_ERROR.getCode(), "用户名不能为空！");
        }
        SysUser sysUser = userService.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(username, sysUser.getPassword(), authorities);
    }
}
