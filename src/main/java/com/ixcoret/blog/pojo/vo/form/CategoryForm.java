package com.ixcoret.blog.pojo.vo.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @author ixcoret
 * @createTime 2021/7/4 15:30
 */
@Data
public class CategoryForm {

    private Integer id;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空！")
    private String name;

    /**
     * 是否启用
     */
    private Boolean enabled;
}
