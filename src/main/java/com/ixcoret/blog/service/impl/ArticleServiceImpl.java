package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.*;
import com.ixcoret.blog.entity.Category;
import com.ixcoret.blog.entity.Tag;
import com.ixcoret.blog.util.PageUtil;
import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.dto.ArticleDTO;
import com.ixcoret.blog.dto.CategoryDTO;
import com.ixcoret.blog.dto.Condition;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.api.Page;
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
    public void save(ArticleDTO articleDTO) {
        CategoryDTO categoryDTO = articleDTO.getCategoryDTO();
        // 新增分类
        if (categoryDTO.getId() == null && categoryDTO.getName().trim().length() > 0) {
            Category category = new Category();
            BeanUtils.copyProperties(categoryDTO, category);
            category.setCreateTime(LocalDateTime.now());
            categoryMapper.save(category);
            categoryDTO.setId(category.getId());
        }

        articleMapper.save(articleDTO);

        List<Tag> tags = articleDTO.getTags();
        // 存标签：(文章id, 标签id)
        if (tags != null && tags.size() != 0) {
            tags = tags.stream().filter(e -> e.getName().trim().length() > 0).collect(Collectors.toList());
            saveTags(articleDTO.getId(), tags);
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
        page.setTotal(total);
        int index = PageUtil.startIndex(condition.getPageNum(), condition.getPageSize());
        List<ArticleBackVO> list = articleMapper.listBackArticles(index, condition.getPageSize());
        page.setList(list);
        return page;
    }
}
