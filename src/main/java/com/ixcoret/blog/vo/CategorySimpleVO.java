package com.ixcoret.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ixcoret
 * @createTime 2021/9/12 18:04
 */
@Data
public class CategorySimpleVO implements Serializable {

    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;
}
