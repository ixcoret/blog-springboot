package com.ixcoret.blog.mapper;

import com.ixcoret.blog.pojo.entity.Category;
import com.ixcoret.blog.pojo.vo.CategoryBackVO;
import com.ixcoret.blog.pojo.vo.form.CategoryForm;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:26
 */
public interface CategoryMapper {

    void save(Category category);

    void update(CategoryForm categoryForm);

    void deleteById(Integer id);

    List<CategoryBackVO> listBackCategories();

    void deleteBatch(List<Integer> ids);
}
