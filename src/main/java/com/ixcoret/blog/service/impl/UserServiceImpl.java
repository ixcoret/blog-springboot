package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.UserMapper;
import com.ixcoret.blog.entity.SysUser;
import com.ixcoret.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ixcoret
 * @createTime 2021/6/13 15:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        SysUser sysUser = userMapper.getUserByUsername(username);
        return sysUser;
    }
}
