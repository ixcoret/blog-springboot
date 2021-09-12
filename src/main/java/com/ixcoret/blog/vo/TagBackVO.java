package com.ixcoret.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ixcoret
 * @createTime 2021/9/7 14:20
 */
@Data
public class TagBackVO implements Serializable {

    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 文章数量
     */
    private Integer articleCount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
