package com.ixcoret.blog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ixcoret
 * @createTime 2021/9/10 17:28
 */
@Data
public class TagDTO {

    private Integer id;

    /**
     * 标签名
     */
    @NotBlank
    @Length(min = 2, message = "名称长度不能小于2")
    private String tagName;
}
