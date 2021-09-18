package com.ixcoret.blog.mapper;

import com.ixcoret.blog.dto.DeleteDTO;
import com.ixcoret.blog.entity.Article;
import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.vo.ArticlePreviewVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:08
 */
@Repository
public interface ArticleMapper {
    void save(Article article);

    List<ArticleBackVO> listBackArticles(int index, int pageSize);

    Integer countArticles();

    void updateArticleDelete(DeleteDTO deleteDTO);

    Article getArticleById(Integer id);

    void update(Article article);

    List<ArticlePreviewVO> listPreviewArticles(int index, Integer pageSize);
}
