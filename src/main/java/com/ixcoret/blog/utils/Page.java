package com.ixcoret.blog.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 *
 * @author ixcoret
 * @date 2021/5/29 13:13
 */
@Data
public class Page<T> implements Serializable {
    /**
     * 当前页
     */
    private Integer pageNumber;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 分页索引
     */
    private Integer index;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 结果集
     */
    private List<T> list;

    /**
     * 搜索条件
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 排序列
     */
    private List<String> sortColumns;

    /**
     * 是否正序排列，默认 true
     */
    private boolean asc = true;

    public Integer getPageNumber() {
        if (this.pageNumber == null) {
            return 1;
        }
        return this.pageNumber;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            return 20;
        }
        return this.pageSize;
    }

    public int getIndex() {
        return (pageNumber - 1) * pageSize;
    }

    public void setPageSize(Integer total) {
        this.total = total;
        this.pageSize = (int)Math.ceil(total * 1.0 / getPageSize());
    }
}
