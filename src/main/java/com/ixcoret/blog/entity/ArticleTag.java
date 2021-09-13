package com.ixcoret.blog.entity;

import lombok.Data;

/**
 * @author ixcoret
 * @createTime 2021/9/7 11:11
 */
@Data
public class ArticleTag {
    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 标签id
     */
    private Integer tagId;
}
