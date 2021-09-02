package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.SysLogMapper;
import com.ixcoret.blog.pojo.entity.SysLog;
import com.ixcoret.blog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ixcoret
 * @createTime 2021/6/8 14:05
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }
}
