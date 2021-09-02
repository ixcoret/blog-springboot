package com.ixcoret.blog.pojo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
    @NotBlank(message = "标签名称不能为空！")
    private String name;
}
