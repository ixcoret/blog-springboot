package com.ixcoret.blog.pojo.vo.form;

import lombok.Data;

/**
 * 查询条件
 * @author ixcoret
 * @createTime 2021/9/3 15:09
 */
@Data
public class Condition {
    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
