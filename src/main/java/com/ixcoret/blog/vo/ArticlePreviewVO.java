package com.ixcoret.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/9/17 8:03
 */
@Data
public class ArticlePreviewVO implements Serializable {

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章分类id
     */
    private CategorySimpleVO category;

    /**
     * 文章标签列表
     */
    private List<TagSimpleVO> tagList;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章浏览量
     */
    private Integer views;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
