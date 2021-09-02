package com.ixcoret.blog.controller;

import com.ixcoret.blog.enums.ResultEnum;
import com.ixcoret.blog.pojo.entity.Category;
import com.ixcoret.blog.pojo.vo.CategoryBackVO;
import com.ixcoret.blog.pojo.vo.form.CategoryForm;
import com.ixcoret.blog.service.CategoryService;
import com.ixcoret.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/15 10:31
 */
@RestController
@Api(tags = "CategoryController")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/categories")
    @ApiOperation("新增分类")
    public Result save(@Valid @RequestBody CategoryForm categoryForm) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryForm, category);
        category.setCreateTime(LocalDateTime.now());
        categoryService.save(category);
        return Result.success();
    }

    @GetMapping("/admin/categories/{id}")
    @ApiOperation("根据id查询")
    public Result<Category> getById(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @PutMapping("/admin/categories")
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryForm categoryForm) {
        categoryService.update(categoryForm);
        return Result.success();
    }

    @DeleteMapping("/admin/categories/{id}")
    @ApiOperation("根据id删除")
    public Result deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/admin/categories")
    @ApiOperation("根据id批量删除")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        if (ids == null || ids.size() == 0) {
            return Result.error(ResultEnum.PARAMS_ERROR);
        }
        categoryService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/admin/categories")
    @ApiOperation("获取后台分类列表")
    public Result<List<CategoryBackVO>> listBackCategories() {
        List<CategoryBackVO> backCategories = categoryService.listBackCategories();
        return Result.success(backCategories);
    }
}
