package com.ixcoret.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志
 * @author ixcoret
 * @createTime 2021/5/29 19:08
 */
@Data
public class OperationLog implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 浏览器信息
     */
    private String browser;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求的controller
     */
    private String controller;

    /**
     * 执行的controller方法
     */
    private String method;

    /**
     * 请求方式：GET POST PUT DETETE
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求状态：1 正常 0异常
     */
    private Integer status;

    /**
     * 返回值
     */
    private String result;

    /**
     * 执行耗时，单位毫秒
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exception;


    /**
     * 创建人
     */
    private String username;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
