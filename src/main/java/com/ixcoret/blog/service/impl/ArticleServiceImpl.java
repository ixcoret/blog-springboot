package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.*;
import com.ixcoret.blog.pojo.entity.Category;
import com.ixcoret.blog.pojo.entity.Tag;
import com.ixcoret.blog.pojo.vo.ArticleBackVO;
import com.ixcoret.blog.pojo.vo.form.ArticleForm;
import com.ixcoret.blog.pojo.vo.form.CategoryForm;
import com.ixcoret.blog.pojo.vo.form.Condition;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(ArticleForm articleForm) {
        CategoryForm categoryForm = articleForm.getCategoryForm();
        // 新增分类
        if (categoryForm.getId() == null && categoryForm.getName().trim().length() > 0) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryForm, category);
            category.setCreateTime(LocalDateTime.now());
            categoryMapper.save(category);
            categoryForm.setId(category.getId());
        }

        articleMapper.save(articleForm);

        List<Tag> tags = articleForm.getTags();
        // 存标签：(文章id, 标签id)
        if (tags != null && tags.size() != 0) {
            tags = tags.stream().filter(e -> e.getName().trim().length() > 0).collect(Collectors.toList());
            saveTags(articleForm.getId(), tags);
        }
    }

    private void saveTags(Integer articleId, List<Tag> tags) {
        // 过滤出新增的标签，存库
        List<Tag> saveList = tags.stream().filter(e -> e.getId() == null).collect(Collectors.toList());
        if (saveList.size() != 0) {
            tagMapper.saveBatch(saveList);
        }
        // 文章、标签的关联关系存库
        articleTagMapper.saveBatch(articleId, tags);
    }

    /**
     * 获取后台分类列表：分页查询
     * @return
     */
    @Override
    public Page<ArticleBackVO> listBackArticles(Condition condition) {
        Integer total = articleMapper.countArticles();
        if (total == 0) {
            return new Page<>();
        }
        Page<ArticleBackVO> page = new Page<>();
        page.setPageSize(condition.getPageSize());
        page.setPageNum(condition.getPageNum());
        page.setPageCount(total);
        Integer index = page.startIndex();
        List<ArticleBackVO> articles = articleMapper.listBackArticles(index, condition.getPageSize());
        page.setResultList(articles);
        return page;
    }
}
