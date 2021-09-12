package com.ixcoret.blog.context;

import com.ixcoret.blog.entity.OperationLog;
import lombok.Data;

/**
 * 解决多线程之间数据隔离问题
 * @author ixcoret
 * @createTime 2021/6/9 14:04
 */
@Data
public class SystemContext {
    private static ThreadLocal<OperationLog> threadLocal = new ThreadLocal<>();

    public static OperationLog getLog() {
        if (threadLocal.get() == null) {
            threadLocal.set(new OperationLog());
        }
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
