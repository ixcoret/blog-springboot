package com.ixcoret.blog.mapper;

import com.ixcoret.blog.pojo.entity.SysUser;

/**
 * @author ixcoret
 * @createTime 2021/6/13 15:39
 */
public interface SysUserMapper {
    /**
     * 根据用户名查询
     * @return
     */
    SysUser getByUsername(String username);
}
