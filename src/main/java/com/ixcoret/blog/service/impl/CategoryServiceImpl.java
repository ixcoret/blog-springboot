package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.CategoryDTO;
import com.ixcoret.blog.dto.Condition;
import com.ixcoret.blog.entity.Category;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.mapper.CategoryMapper;
import com.ixcoret.blog.service.CategoryService;
import com.ixcoret.blog.util.PageUtil;
import com.ixcoret.blog.vo.CategoryBackVO;
import com.ixcoret.blog.vo.CategoryOptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增分类
     *
     * @param categoryDTO
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.selectByName(categoryDTO.getCategoryName());
        if (category != null) {
            throw new BusinessException(ResultCodeEnum.DATA_DUPLICATE.getCode(), "分类名已存在");
        }
        category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.save(category);
    }


    /**
     * 修改分类
     *
     * @param categoryDTO
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = categoryMapper.selectByName(categoryDTO.getCategoryName());
        if (category != null) {
            throw new BusinessException(ResultCodeEnum.DATA_DUPLICATE.getCode(), "分类名已存在");
        }
        category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.update(category);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public Page<CategoryBackVO> listBackCategories(Condition condition) {
        Integer total = categoryMapper.countCategories();
        if (total == 0) {
            return new Page<>();
        }
        Page<CategoryBackVO> page = new Page<>();
        page.setTotal(total);
        int index = PageUtil.startIndex(condition.getPageNum(), condition.getPageSize());
        List<CategoryBackVO> list = categoryMapper.listBackCategories(index, condition.getPageSize());
        page.setList(list);
        return page;
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        categoryMapper.deleteBatch(ids);
    }

    @Override
    public List<CategoryOptionVO> listCategoryOptions() {
        return categoryMapper.listCategoryOptions();
    }
}
