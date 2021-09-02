package com.ixcoret.blog.controller;

import com.ixcoret.blog.pojo.entity.Article;
import com.ixcoret.blog.pojo.vo.form.ArticleForm;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/4 12:36
 */
@Api(tags = "ArticleController")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/admin/articles")
    @ApiOperation("保存文章")
    public Result save(@Valid @RequestBody ArticleForm articleForm) {
        articleService.save(articleForm);
        return Result.success();
    }

    @GetMapping("/admin/articles")
    @ApiOperation("获取后台分类列表")
    public Result<List<Article>> list() {
        return Result.success();
    }
}
