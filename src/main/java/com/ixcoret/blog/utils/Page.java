package com.ixcoret.blog.utils;

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
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 结果集
     */
    private List<T> resultList;

    public Integer getPageNum() {
        if (this.pageNum == null) {
            return 1;
        }
        return this.pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            return 10;
        }
        return this.pageSize;
    }

    public Integer startIndex() {
        return (pageNum -1) * pageSize;
    }

    public void setPageCount(Integer total) {
        this.total = total;
        this.pageCount = (int)Math.ceil(this.total * 1.0 / getPageSize());
    }
}
