package com.ixcoret.blog.service;

import com.ixcoret.blog.entity.OperationLog;

public interface OperationLogService {
    /**
     * 保存日志
     * @param operationLog
     */
    void save(OperationLog operationLog);
}
