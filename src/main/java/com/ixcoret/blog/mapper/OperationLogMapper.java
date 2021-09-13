package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.OperationLog;
import org.springframework.stereotype.Repository;

/**
 * @author ixcoret
 * @createTime 2021/6/8 13:43
 */
@Repository
public interface OperationLogMapper {
    void save(OperationLog operationLog);
}
