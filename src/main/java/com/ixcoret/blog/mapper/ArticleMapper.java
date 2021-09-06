package com.ixcoret.blog.mapper;

import com.ixcoret.blog.pojo.vo.ArticleBackVO;
import com.ixcoret.blog.pojo.vo.form.ArticleForm;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:08
 */
public interface ArticleMapper {
    void save(ArticleForm articleForm);

    List<ArticleBackVO> listBackArticles(Integer index, Integer pageSize );

    Integer countArticles();
}
