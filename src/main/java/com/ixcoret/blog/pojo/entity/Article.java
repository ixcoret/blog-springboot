package com.ixcoret.blog.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ixcoret
 * @createTime 2021/6/30 10:13
 */
@Data
public class Article {
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 文章浏览量
     */
    private Integer views;

    /**
     * 文章发布时间
     */
    private LocalDateTime createTime;

    /**
     * 文章更新时间
     */
    private LocalDateTime updateTime;
}
