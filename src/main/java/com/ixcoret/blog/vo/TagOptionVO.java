package com.ixcoret.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ixcoret
 * @createTime 2021/9/3 19:22
 */
@Data
public class TagOptionVO implements Serializable {

    private Integer id;

    /**
     * 标签名称
     */
    private String name;
}
