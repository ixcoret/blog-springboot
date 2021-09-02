package com.ixcoret.blog.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户
 * @author ixcoret
 * @createTime 2021/6/13 15:07
 */
@Data
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
