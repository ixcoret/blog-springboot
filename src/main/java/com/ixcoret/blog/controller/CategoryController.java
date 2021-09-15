package com.ixcoret.blog.controller;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.dto.CategoryDTO;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.service.CategoryService;
import com.ixcoret.blog.vo.CategoryBackVO;
import com.ixcoret.blog.vo.CategoryOptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result<?> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @PutMapping("/admin/categories")
    @ApiOperation("修改分类")
    public Result<?> update(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @DeleteMapping("/admin/categories/{id}")
    @ApiOperation("根据id删除")
    public Result<?> deleteById(@PathVariable Integer id) {
        if (id == null || id < 1) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        categoryService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/admin/categories")
    @ApiOperation("根据id批量删除")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        categoryService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/admin/categories")
    @ApiOperation("获取后台分类列表")
    public Result<Page<CategoryBackVO>> listBackCategories(ConditionDTO conditionDTO) {
        Page<CategoryBackVO> page = categoryService.listBackCategories(conditionDTO);
        return Result.success(page);
    }

    @GetMapping("/admin/categories/options")
    public Result<List<CategoryOptionVO>> listCategoryOptions() {
        List<CategoryOptionVO> categoryOptions = categoryService.listCategoryOptions();
        return Result.success(categoryOptions);
    }

}
