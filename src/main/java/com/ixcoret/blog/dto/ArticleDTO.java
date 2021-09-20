package com.ixcoret.blog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/4 13:36
 */
@Data
public class ArticleDTO implements Serializable {

    private Integer id;
    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空！")
    @Length(min = 5, message = "文章标题不少于5个字")
    private String title;

    /**
     * 文章分类
     */
    private String categoryName;

    /**
     * 文章标签名列表
     */
    @Valid
    private List<String> tagNameList;

    /**
     * 文章正文
     */
    @NotBlank(message = "文章内容不能为空！")
    private String content;

}
