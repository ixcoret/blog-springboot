package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.Tag;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/7 22:03
 */
public interface ArticleTagMapper {
    void saveBatch(Integer articleId, List<Tag> tags);
}
