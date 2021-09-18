package com.ixcoret.blog.service;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.CategoryDTO;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.vo.CategoryBackVO;
import com.ixcoret.blog.vo.CategorySimpleVO;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:30
 */
public interface CategoryService {
    /**
     * 新增分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 修改分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 列表查询
     * @return
     */
    Page<CategoryBackVO> listBackCategories(ConditionDTO conditionDTO);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    List<CategorySimpleVO> listCategoryOptions();

}
