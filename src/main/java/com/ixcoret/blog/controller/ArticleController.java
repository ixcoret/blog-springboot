package com.ixcoret.blog.controller;

import com.ixcoret.blog.annotation.Log;
import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.dto.ArticleDTO;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.dto.DeleteDTO;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.service.ArticleService;
import com.ixcoret.blog.vo.ArticleBackVO;
import com.ixcoret.blog.vo.ArticleDetailVO;
import com.ixcoret.blog.vo.ArticlePreviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Log
    @PostMapping("/admin/articles")
    @ApiOperation("保存文章")
    public Result<?> save(@Valid @RequestBody ArticleDTO articleDTO) {
        articleService.save(articleDTO);
        return Result.success();
    }

    @GetMapping("/admin/articles")
    @ApiOperation("获取后台文章列表：分页查询")
    public Result<Page<ArticleBackVO>> listBackArticles(ConditionDTO conditionDTO) {
        Page<ArticleBackVO> page = articleService.listBackArticles(conditionDTO);
        return Result.success(page);
    }

    @GetMapping("/admin/articles/{articleId}")
    @ApiOperation("后台根据id获取文章")
    public Result<ArticleDTO> getBackArticleById(@PathVariable("articleId") Integer articleId) {
        if (articleId < 1) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        ArticleDTO article = articleService.getBackArticleById(articleId);
        return Result.success(article);
    }

    @PutMapping("/admin/articles")
    @ApiOperation("恢复或删除文章")
    public Result<?> updateArticleDelete(@Valid @RequestBody DeleteDTO deleteDTO) {
        articleService.updateArticleDelete(deleteDTO);
        return Result.success();
    }

    @ApiOperation("列表查询前台文章")
    @GetMapping("/articles")
    public Result<Page<ArticlePreviewVO>> listPreviewArticles(ConditionDTO conditionDTO) {
        Page<ArticlePreviewVO> page = articleService.listPreviewArticles(conditionDTO);
        return Result.success(page);
    }

    @GetMapping("/articles/{id}")
    @ApiOperation("根据id获取文章详情")
    public Result<ArticleDetailVO> getArticleById(@PathVariable("id") Integer articleId) {
        if (articleId < 1) {
            return Result.error(ResultCodeEnum.PARAMS_ERROR);
        }
        ArticleDetailVO article = articleService.getArticleById(articleId);
        return Result.success(article);
    }
}
