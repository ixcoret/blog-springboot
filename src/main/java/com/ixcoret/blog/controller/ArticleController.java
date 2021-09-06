package com.ixcoret.blog.controller;

import com.ixcoret.blog.pojo.vo.ArticleBackVO;
import com.ixcoret.blog.pojo.vo.form.ArticleForm;
import com.ixcoret.blog.pojo.vo.form.Condition;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.utils.Page;
import com.ixcoret.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    @ApiOperation("获取后台分类列表：分页查询")
    public Result<Page<ArticleBackVO>> listBackArticles(Condition condition) {
        Page<ArticleBackVO> page = articleService.listBackArticles(condition);
        return Result.success(page);
    }
}
