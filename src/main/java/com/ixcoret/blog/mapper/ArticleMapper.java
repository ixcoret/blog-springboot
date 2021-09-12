package com.ixcoret.blog.mapper;

import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.dto.ArticleDTO;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:08
 */
public interface ArticleMapper {
    void save(ArticleDTO articleDTO);

    List<ArticleBackVO> listBackArticles(int index, int pageSize);

    Integer countArticles();
}
