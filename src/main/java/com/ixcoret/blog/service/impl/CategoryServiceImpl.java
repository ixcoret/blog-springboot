package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.CategoryMapper;
import com.ixcoret.blog.pojo.entity.Category;
import com.ixcoret.blog.pojo.vo.CategoryBackVO;
import com.ixcoret.blog.pojo.vo.form.CategoryForm;
import com.ixcoret.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param category
     */
    @Override
    public void save(Category category) {
        categoryMapper.save(category);
    }


    /**
     * 修改分类
     * @param categoryForm
     */
    @Override
    public void update(CategoryForm categoryForm) {
        categoryMapper.update(categoryForm);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public List<CategoryBackVO> listBackCategories() {
        return categoryMapper.listBackCategories();
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        categoryMapper.deleteBatch(ids);
    }
}
