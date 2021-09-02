package com.ixcoret.blog.controller;

import com.ixcoret.blog.pojo.entity.Tag;
import com.ixcoret.blog.service.TagService;
import com.ixcoret.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/admin/tags")
    @ApiOperation("获取后台标签列表")
    public Result<List<Tag>> list() {
        List<Tag> list = tagService.list();
        return Result.success(list);
    }

}
