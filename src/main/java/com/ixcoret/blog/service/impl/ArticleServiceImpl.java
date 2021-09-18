package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.ArticleDTO;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.dto.DeleteDTO;
import com.ixcoret.blog.entity.Article;
import com.ixcoret.blog.entity.ArticleTag;
import com.ixcoret.blog.entity.Category;
import com.ixcoret.blog.entity.Tag;
import com.ixcoret.blog.mapper.ArticleMapper;
import com.ixcoret.blog.mapper.ArticleTagMapper;
import com.ixcoret.blog.mapper.CategoryMapper;
import com.ixcoret.blog.mapper.TagMapper;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.util.PageUtil;
import com.ixcoret.blog.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ixcoret
 * @createTime 2021/7/6 21:08
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void save(ArticleDTO articleDTO) {

        Category category = saveArticleCategory(articleDTO);

        Article article = new Article();
        if (category != null) {
            article.setCategoryId(category.getId());
        }
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdateTime(LocalDateTime.now());
        if (articleDTO.getId() == null) {
            article.setViews(0);
            article.setDeleted(false);
            article.setCreateTime(LocalDateTime.now());
            articleMapper.save(article);
        } else {
            article.setId(articleDTO.getId());
            articleMapper.update(article);
        }

        saveArticleTag(articleDTO, article.getId());
    }

    private Category saveArticleCategory(ArticleDTO articleDTO) {
        if (articleDTO.getId() != null) {
            articleTagMapper.deleteBatch(articleDTO.getId());
        }
        String categoryName = articleDTO.getCategoryName();
        if (categoryName == null || categoryName.trim().equals("")) {
            return null;
        }
        Category category = categoryMapper.getByName(categoryName);
        if (category != null) {
            return category;
        }
        category = new Category();
        category.setCategoryName(articleDTO.getCategoryName());
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.save(category);
        return category;
    }

    private void saveArticleTag(ArticleDTO articleDTO, Integer articleId) {
        List<String> tagNameList = articleDTO.getTagNameList();
        if (!CollectionUtils.isEmpty(tagNameList)) {
            List<Tag> existTagList = tagMapper.listTagsInTagNameList(tagNameList);
            List<String> existTagNameList = existTagList.stream().map(Tag::getTagName).collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream().map(Tag::getId).collect(Collectors.toList());
            tagNameList.removeAll(existTagNameList);
            if (!CollectionUtils.isEmpty(tagNameList)) {
                List<Tag> newTagList = tagNameList.stream().map(e -> {
                    Tag tag = new Tag();
                    tag.setTagName(e);
                    tag.setCreateTime(LocalDateTime.now());
                    return tag;
                }).collect(Collectors.toList());
                tagMapper.saveBatch(newTagList);
                List<Integer> newTagIdList = newTagList.stream().map(Tag::getId).collect(Collectors.toList());
                existTagIdList.addAll(newTagIdList);
            }
            List<ArticleTag> articleTagList = existTagIdList.stream().map(e -> {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(e);
                return articleTag;
            }).collect(Collectors.toList());
            articleTagMapper.saveBatch(articleTagList);
        }
    }

    /**
     * 获取后台分类列表：分页查询
     * @return
     */
    @Override
    public Page<ArticleBackVO> listBackArticles(ConditionDTO conditionDTO) {
        Integer total = articleMapper.countArticles();
        if (total == 0) {
            return new Page<>();
        }
        Page<ArticleBackVO> page = new Page<>();
        page.setTotal(total);
        int index = PageUtil.startIndex(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        List<ArticleBackVO> list = articleMapper.listBackArticles(index, conditionDTO.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public void updateArticleDelete(DeleteDTO deleteDTO) {
        articleMapper.updateArticleDelete(deleteDTO);
    }

    @Override
    public ArticleDTO getBackArticleById(Integer articleId) {
        Article article = articleMapper.getArticleById(articleId);
        if (article == null) {
            return null;
        }
        Category category = categoryMapper.getById(article.getCategoryId());
        List<String> tagNameList = tagMapper.listTagNameByArticleId(articleId);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setCategoryName(category.getCategoryName());
        articleDTO.setTagNameList(tagNameList);
        articleDTO.setContent(article.getContent());
        return articleDTO;
    }

    @Override
    public Page<ArticlePreviewVO> listPreviewArticles(ConditionDTO conditionDTO) {
        Integer total = articleMapper.countArticles();
        if (total == 0) {
            return new Page<>();
        }
        Page<ArticlePreviewVO> page = new Page<>();
        page.setTotal(total);
        int index = PageUtil.startIndex(conditionDTO.getPageNum(), conditionDTO.getPageSize());
        List<ArticlePreviewVO> frontArticles = articleMapper.listPreviewArticles(index, conditionDTO.getPageSize());
        page.setList(frontArticles);
        return page;
    }

    @Override
    public ArticleDetailVO getArticleById(Integer articleId) {
        Article article = articleMapper.getArticleById(articleId);
        if (article == null) {
            return null;
        }
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();
        BeanUtils.copyProperties(article, articleDetailVO);
        Category category = categoryMapper.getById(article.getCategoryId());
        CategorySimpleVO categorySimpleVO = new CategorySimpleVO();
        BeanUtils.copyProperties(category, categorySimpleVO);
        articleDetailVO.setCategory(categorySimpleVO);
        List<TagSimpleVO> tagList = tagMapper.getByArticleId(articleId);
        articleDetailVO.setTagList(tagList);
        return articleDetailVO;
    }
}
