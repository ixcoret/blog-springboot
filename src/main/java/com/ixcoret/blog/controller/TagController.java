package com.ixcoret.blog.controller;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.dto.TagDTO;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.service.TagService;
import com.ixcoret.blog.vo.TagBackVO;
import com.ixcoret.blog.vo.TagOptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/5 0:13
 */
@RestController
@Api(tags = "TagController")
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation("后台标签列表")
    @GetMapping("/admin/tags/")
    public Result<Page<TagBackVO>> listBackTags(ConditionDTO conditionDTO) {
        Page<TagBackVO> page = tagService.listBackTags(conditionDTO);
        return Result.success(page);
    }

    @DeleteMapping("/admin/tags/{id}")
    @ApiOperation("根据id删除")
    public Result<?> deleteById(@PathVariable Integer id) {
        if (id == null || id < 1) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        tagService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/admin/tags/")
    @ApiOperation("根据id批量删除")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        tagService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/admin/tags/")
    @ApiOperation("修改标签")
    public Result<?> updata(@Valid @RequestBody TagDTO tagDTO) {
        tagService.update(tagDTO);
        return Result.success();
    }

    @PostMapping("/admin/tags/")
    @ApiOperation("新增标签")
    public Result<?> save(@Valid @RequestBody TagDTO tagDTO) {
        tagService.save(tagDTO);
        return Result.success();
    }

    /**
     * 后台管理系统 文章标签简略信息列表查询：仅包含id和name字段，用于发布文章式，列出所有已存在的文章标签
     * @return
     */
    @ApiOperation("后台简略标签列表")
    @GetMapping("/admin/tags/options")
    public Result<List<TagOptionVO>> listTagOptions() {
        List<TagOptionVO> tagOptions = tagService.listTagOptions();
        return Result.success(tagOptions);
    }
}
