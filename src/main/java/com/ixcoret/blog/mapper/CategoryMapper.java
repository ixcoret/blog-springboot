package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.Category;
import com.ixcoret.blog.vo.CategoryBackVO;
import com.ixcoret.blog.vo.CategorySimpleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:26
 */
@Repository
public interface CategoryMapper {

    void save(Category category);

    void update(Category category);

    Category getByName(String name);

    void deleteById(Integer id);

    Integer countCategories();

    List<CategoryBackVO> listBackCategories(int index, int pageSize);

    void deleteBatch(List<Integer> ids);

    List<CategorySimpleVO> listCategoryOptions();

    Category getById(Integer id);
}
