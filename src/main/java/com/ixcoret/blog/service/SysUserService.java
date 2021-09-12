package com.ixcoret.blog.service;

import com.ixcoret.blog.entity.SysUser;

/**
 * @author ixcoret
 * @createTime 2021/6/13 15:45
 */
public interface SysUserService {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUser getByUsername(String username);
}
