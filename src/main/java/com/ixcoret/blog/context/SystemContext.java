package com.ixcoret.blog.context;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author ixcoret
 * @createTime 2021/6/9 14:04
 */
@Data
public class SystemContext {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static User getLoginUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
