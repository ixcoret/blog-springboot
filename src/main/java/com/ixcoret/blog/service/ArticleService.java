package com.ixcoret.blog.service;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.ArticleDTO;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.dto.DeleteDTO;
import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.vo.ArticleDetailVO;
import com.ixcoret.blog.vo.ArticlePreviewVO;

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
    Page<ArticleBackVO> listBackArticles(ConditionDTO conditionDTO);

    void updateArticleDelete(DeleteDTO deleteDTO);

    ArticleDTO getBackArticleById(Integer articleId);

    Page<ArticlePreviewVO> listPreviewArticles(ConditionDTO conditionDTO);

    ArticleDetailVO getArticleById(Integer articleId);
}
