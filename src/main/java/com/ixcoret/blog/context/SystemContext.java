package com.ixcoret.blog.context;

import com.ixcoret.blog.pojo.entity.SysLog;
import lombok.Data;

/**
 * 解决多线程之间数据隔离问题
 * @author ixcoret
 * @createTime 2021/6/9 14:04
 */
@Data
public class SystemContext {
    private static ThreadLocal<SysLog> threadLocal = new ThreadLocal<>();

    public static SysLog getLog() {
        if (threadLocal.get() == null) {
            threadLocal.set(new SysLog());
        }
        return threadLocal.get();
    }
}
