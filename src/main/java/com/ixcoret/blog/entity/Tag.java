package com.ixcoret.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ixcoret
 * @createTime 2021/6/30 10:09
 */
@Data
public class Tag implements Serializable {
    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
