package com.ixcoret.blog.service;

import com.ixcoret.blog.pojo.vo.ArticleBackVO;
import com.ixcoret.blog.pojo.vo.form.ArticleForm;
import com.ixcoret.blog.pojo.vo.form.Condition;
import com.ixcoret.blog.utils.Page;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:07
 */
public interface ArticleService {
    void save(ArticleForm articleForm);

    /**
     * 获取后台分类列表：分页查询
     * @return
     */
    Page<ArticleBackVO> listBackArticles(Condition condition);
}
