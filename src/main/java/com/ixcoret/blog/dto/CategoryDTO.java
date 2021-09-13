package com.ixcoret.blog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ixcoret
 * @createTime 2021/7/4 15:30
 */
@Data
public class CategoryDTO {

    private Integer id;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    @Length(min = 2, message = "名称长度不能小于2")
    private String categoryName;

}
