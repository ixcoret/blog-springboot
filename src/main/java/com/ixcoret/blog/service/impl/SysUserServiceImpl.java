package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.SysUserMapper;
import com.ixcoret.blog.pojo.entity.SysUser;
import com.ixcoret.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ixcoret
 * @createTime 2021/6/13 15:45
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        SysUser sysUser = sysUserMapper.getByUsername(username);
        return sysUser;
    }
}
