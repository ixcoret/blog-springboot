package com.ixcoret.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/9/14 22:30
 */
@Data
public class DeleteDTO {

    @NotNull(message = "id不能为空")
    @Size(min = 1)
    private List<Integer> idList;

    @NotNull(message = "状态值不能为空")
    private Boolean deleted;
}
