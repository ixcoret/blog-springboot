package com.ixcoret.blog.dto;

import com.ixcoret.blog.entity.Tag;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/4 13:36
 */
@Data
public class ArticleDTO {
    private Integer id;
    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空！")
    private String title;

    /**
     * 文章分类
     */
    private CategoryDTO categoryDTO;

    /**
     * 文章标签列表
     */
    @Valid
    private List<Tag> tags;

    /**
     * 文章正文
     */
    @Valid
    private String content;

}
