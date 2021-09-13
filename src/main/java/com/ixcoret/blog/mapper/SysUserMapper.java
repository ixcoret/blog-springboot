package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @author ixcoret
 * @createTime 2021/6/13 15:39
 */
@Repository
public interface SysUserMapper {
    /**
     * 根据用户名查询
     * @return
     */
    SysUser getByUsername(String username);
}
