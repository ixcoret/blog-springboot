package com.ixcoret.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ixcoret
 * @createTime 2021/9/2 12:01
 */
@Data
public class ArticleBackVO implements Serializable {

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章分类名称
     */
    private String categoryName;

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
