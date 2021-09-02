package com.ixcoret.blog.service;

import com.ixcoret.blog.pojo.entity.Category;
import com.ixcoret.blog.pojo.vo.CategoryBackVO;
import com.ixcoret.blog.pojo.vo.form.CategoryForm;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:30
 */
public interface CategoryService {
    /**
     * 新增分类
     * @param categoryForm
     */
    void save(Category category);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 修改分类
     * @param category
     */
    void update(CategoryForm categoryForm);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 列表查询
     * @return
     */
    List<CategoryBackVO> listBackCategories();

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteBatch(List<Integer> ids);
}
