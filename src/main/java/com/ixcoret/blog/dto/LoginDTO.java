package com.ixcoret.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ixcoret
 * @createTime 2021/7/3 19:38
 */
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空！")
    private String password;
}
