package com.ixcoret.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ixcoret
 * @createTime 2021/10/10 15:30
 */
@Data
public class UserVO implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
