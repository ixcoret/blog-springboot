package com.ixcoret.blog.security;

import com.ixcoret.blog.entity.SysUser;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载用户信息
 *
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
            // 出于安全性考虑，UsernameNotFoundException异常默认会被隐藏，使用BadCredentialsException异常代替
            throw new UsernameNotFoundException(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(username, sysUser.getPassword(), authorities);
    }
}
