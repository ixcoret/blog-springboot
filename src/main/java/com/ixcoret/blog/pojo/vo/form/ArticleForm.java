package com.ixcoret.blog.pojo.vo.form;

import com.ixcoret.blog.pojo.entity.Tag;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/4 13:36
 */
@Data
public class ArticleForm {
    private Integer id;
    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空！")
    private String title;

    /**
     * 文章分类
     */
    private CategoryForm categoryForm;

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
