package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.OperationLogMapper;
import com.ixcoret.blog.entity.OperationLog;
import com.ixcoret.blog.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ixcoret
 * @createTime 2021/6/8 14:05
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public void save(OperationLog operationLog) {
        operationLogMapper.save(operationLog);
    }
}
