package com.ixcoret.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 博客分类
 * @author ixcoret
 * @createTime 2021/6/15 9:59
 */
@Data
public class Category {
    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
