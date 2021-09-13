package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.ArticleTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/7 22:03
 */
@Repository
public interface ArticleTagMapper {
    void saveBatch(List<ArticleTag> articleTagList);
}
