package com.ixcoret.blog.pojo.entity;

import lombok.Data;

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
}
