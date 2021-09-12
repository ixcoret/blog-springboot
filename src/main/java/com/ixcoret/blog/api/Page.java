package com.ixcoret.blog.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author ixcoret
 * @createTime 2021/5/29 13:13
 */
@Data
public class Page<T> implements Serializable {
    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 结果集
     */
    private List<T> list;

}
