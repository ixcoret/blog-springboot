package com.ixcoret.blog.service;

import com.ixcoret.blog.pojo.entity.SysLog;

public interface SysLogService {
    /**
     * 保存日志
     * @param sysLog
     */
    void save(SysLog sysLog);
}
