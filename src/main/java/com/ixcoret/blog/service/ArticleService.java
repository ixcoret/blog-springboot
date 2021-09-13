package com.ixcoret.blog.service;

import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.dto.ArticleDTO;
import com.ixcoret.blog.dto.Condition;
import com.ixcoret.blog.api.Page;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:07
 */
public interface ArticleService {
    /**
     * 保存文章
     * @param articleDTO
     */
    void save(ArticleDTO articleDTO);

    /**
     * 获取后台分类列表：分页查询
     * @return
     */
    Page<ArticleBackVO> listBackArticles(Condition condition);
}
