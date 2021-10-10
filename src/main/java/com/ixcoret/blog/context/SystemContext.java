package com.ixcoret.blog.context;

import com.ixcoret.blog.entity.OperationLog;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * 解决多线程之间数据隔离问题
 * @author ixcoret
 * @createTime 2021/6/9 14:04
 */
@Data
public class SystemContext {
    private static ThreadLocal<OperationLog> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前线程的操作日志对象
     *
     * @return
     */
    public static OperationLog getOperationLog() {
        if (threadLocal.get() == null) {
            threadLocal.set(new OperationLog());
        }
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static User getLoginUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
